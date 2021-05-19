package com.mironov.image.studio.api.services;

import com.mironov.image.studio.api.dto.UserCreateDto;

public interface IActivationService {

    UserCreateDto activationUser(String email);

}
