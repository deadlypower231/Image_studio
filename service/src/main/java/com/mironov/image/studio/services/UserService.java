package com.mironov.image.studio.services;

import com.mironov.image.studio.api.dao.IRoleDao;
import com.mironov.image.studio.api.dao.IUserDao;
import com.mironov.image.studio.api.dto.*;
import com.mironov.image.studio.api.mappers.RoleMapper;
import com.mironov.image.studio.api.mappers.UserCreateMapper;
import com.mironov.image.studio.api.mappers.UserMapper;
import com.mironov.image.studio.api.services.IUserService;
import com.mironov.image.studio.entities.Role;
import com.mironov.image.studio.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final IUserDao userDao;
    private final IRoleDao roleDao;

    public UserService(PasswordEncoder passwordEncoder, IUserDao userDao, IRoleDao roleDao) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    public UserDto getUser(long id) {
        return UserMapper.mapUserDto(this.userDao.get(id));
    }

    @Override
    public UserDto findUserByName(String name) {
        return UserMapper.mapUserDto(this.userDao.getByName(name));
    }

    @Override
    public List<UserDto> getAll() {
        return UserMapper.mapUsersDto(this.userDao.getAll());
    }

    @Override
    @Transactional
    public void delete(long id) {
        this.userDao.delete(this.userDao.get(id));
    }

    @Override
    @Transactional
    public void updateUserRoles(UserRolesDto userRolesDto, long id) {
        User user = this.userDao.get(id);
        List<Role> rolesDB = this.roleDao.getAll();
        rolesDB.removeIf(x -> !userRolesDto.getRoles().toString().contains(x.toString()));
        user.setRoles(rolesDB);
        this.userDao.update(user);
    }

    @Transactional
    public void createUser(UserCreateDto userDto) {
        UserCreateDto savedUser = UserCreateMapper.mapUserDto(this.userDao.create(UserCreateMapper.mapCreateUser(userDto)));
//        UserCreateDto savedUser = UserMapper.mapUserDto(this.userDao.create(UserCreateMapper.mapUser(userDto)));
        List<RoleDto> roles = RoleMapper.mapRolesDto(this.roleDao.getAll());
        savedUser.getRoles().add(roles.get(0));
        savedUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        this.userDao.update(UserCreateMapper.mapUser(savedUser));
    }

    @Override
    public List<UserDto> getAllMasters() {
        return UserMapper.mapUsersDto(this.userDao.getAllMasters());
    }

    @Override
    public List<UserDto> searchUsers(String text) {
        return UserMapper.mapUsersDto(this.userDao.searchUsers(text));
    }

    @Override
    public Set<UserDto> searchMasters(String text) {
        List<String> strings = Arrays.asList(text.split(" "));
        return UserMapper.mapUsersDto(this.userDao.searchMasters(strings));
    }

    @Override
    @Transactional
    public void updateUser(UserUpdateDto userUpdateDto) {
        User entity = this.userDao.getByName(userUpdateDto.getUsername());
        entity.setUsername(userUpdateDto.getUsername());
        entity.setFirstName(userUpdateDto.getFirstName());
        entity.setLastName(userUpdateDto.getLastName());
        entity.setEmail(userUpdateDto.getEmail());
        entity.setPhone(userUpdateDto.getPhone());
        this.userDao.update(entity);
    }

}
