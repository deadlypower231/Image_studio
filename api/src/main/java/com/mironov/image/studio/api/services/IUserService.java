package com.mironov.image.studio.api.services;

import com.mironov.image.studio.api.dto.UserDto;
import com.mironov.image.studio.api.dto.UserRolesDto;
import com.mironov.image.studio.entities.User;

import java.util.List;

public interface IUserService {
    User getUser (long id);
    UserDto createUser(UserDto userDto);
    void addUserRole(UserDto userDto);
    UserDto findUserByName(String name);
    List<UserDto> getAll();
    void delete(long id);
    void updateUserRoles(UserRolesDto userRolesDto, long id);
}
