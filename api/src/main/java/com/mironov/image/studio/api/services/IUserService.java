package com.mironov.image.studio.api.services;

import com.mironov.image.studio.api.dto.*;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IUserService {

    UserDto getUser(long id);

    void createUser(UserCreateDto userDto);

    boolean findUserByName(String name);

    boolean findUserByNumberPhone(String phone);

    boolean findUserByEmail(String email);

    boolean checkValidEmail(String email);

    List<UserDto> getAll();

    void delete(long id);

    void updateUserRoles(UserRolesDto userRolesDto, long id);

    List<UserDto> getAllMasters();

    Set<UserDto> searchMasters(String text);

    void updateUser(UserUpdateDto userUpdateDto);

    void updateUserImage(MultipartFile multipartFile, long id);

    void updateUserDescription(DescriptionDto descriptionDto, long id);

    boolean createNewPassword(EmailDto email);

    void updateStatus(long id);

    void sendMessage(CurrentUserDto user, SendMessageDto sendMessageDto);

    Map<String, Object> findPaginatedUsers(Pageable pageable);

    Map<String, Object> findPaginatedUsersSearch(Pageable pageable, String text);

}
