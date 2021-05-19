package com.mironov.image.studio.api.utils;

import com.mironov.image.studio.api.dto.CurrentUserDto;
import com.mironov.image.studio.api.dto.SendMessageDto;
import com.mironov.image.studio.entities.Order;
import com.mironov.image.studio.entities.User;

public interface IEmailSender {

    void sendEmailFromAdmin(User user, String encoding);

    void sendEmailFromAdminByOrder(User user, Order order);

    void sendEmailWithNewPasswordFromAdmin(User user, String password);

    void sendEmailFromMasterToUser(CurrentUserDto user, SendMessageDto sendMessage);

}
