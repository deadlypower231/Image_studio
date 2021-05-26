package com.mironov.image.studio.api.services;

import com.mironov.image.studio.api.dto.CurrentUserDto;
import com.mironov.image.studio.entities.User;

public interface ISecurityService {

    CurrentUserDto findLoggedInUser();

    void autoLogin(String username, String password);

    void changeUsername(String username, String password);

    void changePrincipal(User user);

}
