package com.mironov.image.studio.api.services;

import com.mironov.image.studio.api.dto.UserCreateDto;
import com.mironov.image.studio.api.dto.UserDto;

public interface IActivationService {

    UserCreateDto activationUser(String email);

}
