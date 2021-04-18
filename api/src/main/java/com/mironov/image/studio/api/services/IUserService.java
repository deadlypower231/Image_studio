package com.mironov.image.studio.api.services;

import com.mironov.image.studio.api.dto.UserDto;
import com.mironov.image.studio.entities.User;

public interface IUserService {
    User getUser (long id);
    UserDto createUser(UserDto userDto);
    void addUserRole(UserDto userDto);
}
