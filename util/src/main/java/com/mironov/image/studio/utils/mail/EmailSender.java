package com.mironov.image.studio.utils.mail;

import com.mironov.image.studio.api.utils.IEmailSender;
import com.mironov.image.studio.entities.User;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.StringWriter;

@Component
public class EmailSender implements IEmailSender {

    @Value("${mail.sender.set.username}")
    private String adminEmailAddress;

    private final VelocityEngine velocityEngine;
    private final JavaMailSender mailSender;

    public EmailSender(VelocityEngine velocityEngine, JavaMailSender mailSender) {
        this.velocityEngine = velocityEngine;
        this.mailSender = mailSender;
    }


    @Async
    @Override
    public void sendEmailFromAdmin(User user, String encoding) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        String text = prepareActivateRequestEmail(user, encoding);
        String subject = new StringBuilder("Активация аккаунта ").append(user.getUsername()).append(".").toString();
        configureMimeMessageHelper(helper, user.getEmail(), adminEmailAddress, text, subject);
        mailSender.send(message);
    }

    @Override
    public void sendEmailWithNewPasswordFromAdmin(User user, String password) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        String text = prepareNewPasswordRequestEmail(user, password);
        String subject = new StringBuilder("Смена текущего пароля пользователя ").append(user.getUsername()).append(".").toString();
        configureMimeMessageHelper(helper, user.getEmail(), adminEmailAddress, text, subject);
        mailSender.send(message);
    }

    private void configureMimeMessageHelper(MimeMessageHelper helper, String mailFrom, String mailTo, String mailText,
                                            String mailSubject) throws MessagingException {

        helper.setFrom(mailFrom);
        helper.setTo(mailTo);
        helper.setText(mailText, true);
        helper.setSubject(mailSubject);
    }

    private String prepareActivateRequestEmail(User user, String encoding) {
        VelocityContext context = createVelocityContextWithBasicParameters(user, encoding);
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate("mailtemplates/activate.vm", "UTF-8", context, stringWriter);
        return stringWriter.toString();
    }

    private String prepareNewPasswordRequestEmail(User user, String password){
        VelocityContext context = createVelocityContextWithBasicParameters(user, password);
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate("mailtemplates/newPassword.vm", "UTF-8", context, stringWriter);
        return stringWriter.toString();
    }

    private VelocityContext createVelocityContextWithBasicParameters(User user, String source) {
        VelocityContext context = new VelocityContext();
        context.put("firstName", user.getFirstName());
        context.put("lastName", user.getLastName());
        context.put("source", source);
        return context;
    }

}
