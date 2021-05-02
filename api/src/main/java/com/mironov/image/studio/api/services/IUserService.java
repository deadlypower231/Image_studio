package com.mironov.image.studio.api.services;

import com.mironov.image.studio.api.dto.UserCreateDto;
import com.mironov.image.studio.api.dto.UserDto;
import com.mironov.image.studio.api.dto.UserRolesDto;
import com.mironov.image.studio.api.dto.UserUpdateDto;

import java.util.List;
import java.util.Set;

public interface IUserService {
    UserDto getUser(long id);

    void createUser(UserCreateDto userDto);

    UserDto findUserByName(String name);

    List<UserDto> getAll();

    void delete(long id);

    void updateUserRoles(UserRolesDto userRolesDto, long id);

    List<UserDto> getAllMasters();

    List<UserDto> searchUsers(String text);

    Set<UserDto> searchMasters(String text);

    void updateUser(UserUpdateDto userUpdateDto);
}
