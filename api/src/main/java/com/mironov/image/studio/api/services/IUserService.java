package com.mironov.image.studio.api.services;

import com.mironov.image.studio.api.dto.*;

import java.util.List;
import java.util.Set;

public interface IUserService {
    UserDto getUser(long id);

    void createUser(UserCreateDto userDto);

    boolean findUserByName(String name);

    boolean findUserByNumberPhone(long phone);

    boolean findUserByEmail(String email);

    List<UserDto> getAll();

    void delete(long id);

    void updateUserRoles(UserRolesDto userRolesDto, long id);

    List<UserDto> getAllMasters();

    List<UserDto> searchUsers(String text);

    Set<UserDto> searchMasters(String text);

    void updateUser(UserUpdateDto userUpdateDto);

    boolean createNewPassword(EmailDto email);
}
