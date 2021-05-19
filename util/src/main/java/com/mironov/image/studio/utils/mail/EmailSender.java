package com.mironov.image.studio.utils.mail;

import com.mironov.image.studio.api.dto.CurrentUserDto;
import com.mironov.image.studio.api.dto.SendMessageDto;
import com.mironov.image.studio.api.utils.IEmailSender;
import com.mironov.image.studio.entities.Order;
import com.mironov.image.studio.entities.User;
import lombok.extern.log4j.Log4j2;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;

@Component
@Log4j2
public class EmailSender implements IEmailSender {

    private static final String UTF_8 = "UTF-8";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String FAILED_CONFIGURE_MESSAGE = "Failed configure message {}";

    private final VelocityEngine velocityEngine;
    private final JavaMailSender mailSender;
    @Value("${mail.sender.set.username}")
    private String adminEmailAddress;

    public EmailSender(VelocityEngine velocityEngine, JavaMailSender mailSender) {
        this.velocityEngine = velocityEngine;
        this.mailSender = mailSender;
    }

    @Async
    @Override
    public void sendEmailFromAdmin(User user, String encoding) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, UTF_8);
        String text = prepareActivateRequestEmail(user, encoding);
        String subject = "Активация аккаунта " + user.getUsername() + ".";
        try {
            configureMimeMessageHelper(helper, adminEmailAddress, user.getEmail(), text, subject);
            mailSender.send(message);
        }catch (MailException e){
            log.error("Failed to send message to mail {}", user.getEmail());
        }catch (MessagingException e){
            log.error(FAILED_CONFIGURE_MESSAGE, e.getMessage());
        }
    }

    @Async
    @Override
    public void sendEmailFromAdminByOrder(User user, Order order) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, UTF_8);
        String text = prepareCreateOrderRequestEmail(user, order);
        String subject = "Ваш заказ №" + order.getId() + " успешно принят!";
        try {
            configureMimeMessageHelper(helper, adminEmailAddress, user.getEmail(), text, subject);
            mailSender.send(message);
        } catch (MailException e) {
            log.error("Failed to send message(from admin): {}", e.getMessage());
        } catch (MessagingException e) {
            log.error(FAILED_CONFIGURE_MESSAGE, e.getMessage());
        }
    }

    @Async
    @Override
    public void sendEmailWithNewPasswordFromAdmin(User user, String password) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, UTF_8);
        String text = prepareNewPasswordRequestEmail(user, password);
        String subject = "Смена текущего пароля пользователя " + user.getUsername() + ".";
        try {
            configureMimeMessageHelper(helper, adminEmailAddress, user.getEmail(), text, subject);
            mailSender.send(message);
        } catch (MailException e) {
            log.error("Failed to send message(newPassword): mail - {}", user.getEmail());
        } catch (MessagingException e) {
            log.error(FAILED_CONFIGURE_MESSAGE, e.getMessage());
        }

    }

    @Override
    @Async
    public void sendEmailFromMasterToUser(CurrentUserDto user, SendMessageDto sendMessage) {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, UTF_8);
        String text = prepareSendMessageRequestEmail(user, sendMessage);
        String subject = "Image studio, мастер: " + user.getFirstName() + " " + user.getLastName() + ".";
        try {
            configureMimeMessageHelper(helper, user.getEmail(), sendMessage.getEmail(), text, subject);
            mailSender.send(message);
        } catch (MailException e) {
            log.error("Failed to send message to mail {}", sendMessage.getEmail());
        } catch (MessagingException e) {
            log.error(FAILED_CONFIGURE_MESSAGE, e.getMessage());
        }
    }

    private void configureMimeMessageHelper(MimeMessageHelper helper, String mailFrom, String mailTo, String mailText,
                                            String mailSubject) throws MessagingException {
        helper.setFrom(mailFrom);
        helper.setTo(mailTo);
        helper.setText(mailText, true);
        helper.setSubject(mailSubject);
    }

    private String prepareCreateOrderRequestEmail(User user, Order order) {
        VelocityContext context = createVelocityContextWithBasicParametersByOrder(user, order);
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate("templates/mail/order.vm", UTF_8, context, stringWriter);
        return stringWriter.toString();
    }

    private String prepareActivateRequestEmail(User user, String encoding) {
        VelocityContext context = createVelocityContextWithBasicParameters(user, encoding);
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate("templates/mail/activate.vm", UTF_8, context, stringWriter);
        return stringWriter.toString();
    }

    private String prepareNewPasswordRequestEmail(User user, String password) {
        VelocityContext context = createVelocityContextWithBasicParameters(user, password);
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate("templates/mail/newPassword.vm", UTF_8, context, stringWriter);
        return stringWriter.toString();
    }

    private String prepareSendMessageRequestEmail(CurrentUserDto user, SendMessageDto sendMessage) {
        VelocityContext context = createVelocityContextWithBasicParametersSendMessage(user, sendMessage.getText());
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate("templates/mail/sendMessage.vm", UTF_8, context, stringWriter);
        return stringWriter.toString();
    }

    private VelocityContext createVelocityContextWithBasicParameters(User user, String source) {
        VelocityContext context = new VelocityContext();
        context.put(FIRST_NAME, user.getFirstName());
        context.put(LAST_NAME, user.getLastName());
        context.put("source", source);
        return context;
    }

    private VelocityContext createVelocityContextWithBasicParametersByOrder(User user, Order order) {
        VelocityContext context = new VelocityContext();
        context.put(FIRST_NAME, user.getFirstName());
        context.put(LAST_NAME, user.getLastName());
        context.put("firstNameMaster", order.getMaster().getFirstName());
        context.put("lastNameMaster", order.getMaster().getLastName());
        context.put("service", order.getMasterService().getName());
        context.put("price", order.getPrice());
        context.put("tournamentName", order.getTournament().getName());
        context.put("tournamentAddress", order.getTournament().getAddress());
        context.put("tournamentDate", order.getTournament().getDate());
        context.put("tournamentTime", order.getSchedule().getTime());
        return context;
    }

    private VelocityContext createVelocityContextWithBasicParametersSendMessage(CurrentUserDto user, String source) {
        VelocityContext context = new VelocityContext();
        context.put(FIRST_NAME, user.getFirstName());
        context.put(LAST_NAME, user.getLastName());
        context.put("source", source);
        return context;
    }

}
