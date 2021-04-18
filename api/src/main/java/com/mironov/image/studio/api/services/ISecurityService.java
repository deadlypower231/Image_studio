package com.mironov.image.studio.api.services;

public interface ISecurityService {
    String findLoggedInUser();
    void autoLogin(String username, String password);
}
