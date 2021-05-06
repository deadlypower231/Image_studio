package com.mironov.image.studio.api.utils;

import com.mironov.image.studio.api.dto.UserCreateDto;
import com.mironov.image.studio.api.dto.UserDto;
import com.mironov.image.studio.api.mappers.UserCreateMapper;
import com.mironov.image.studio.entities.User;

public interface IEmailSender {

//    void sendEmailToAdmin(User user, String encoding) throws Exception;

    void sendEmailFromAdmin(User user, String encoding) throws Exception;

    void sendEmailWithNewPasswordFromAdmin(User user, String password) throws Exception;

}
