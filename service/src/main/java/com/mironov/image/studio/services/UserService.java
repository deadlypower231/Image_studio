package com.mironov.image.studio.services;

import com.mironov.image.studio.api.dao.IRoleDao;
import com.mironov.image.studio.api.dao.IUserDao;
import com.mironov.image.studio.api.dto.*;
import com.mironov.image.studio.api.mappers.RoleMapper;
import com.mironov.image.studio.api.mappers.UserCreateMapper;
import com.mironov.image.studio.api.mappers.UserMapper;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.IUserService;
import com.mironov.image.studio.api.utils.IEmailSender;
import com.mironov.image.studio.entities.Role;
import com.mironov.image.studio.entities.User;
import com.mironov.image.studio.enums.Status;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.security.Principal;
import java.time.OffsetDateTime;
import java.util.*;

@Service
@Log4j2
public class UserService implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final IUserDao userDao;
    private final IRoleDao roleDao;
    private final IEmailSender emailSender;
    private final ISecurityService securityService;

    public UserService(PasswordEncoder passwordEncoder, IUserDao userDao, IRoleDao roleDao, IEmailSender emailSender, ISecurityService securityService) {
        this.passwordEncoder = passwordEncoder;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.emailSender = emailSender;
        this.securityService = securityService;
    }

    @Override
    public UserDto getUser(long id) {
        return UserMapper.mapUserDto(this.userDao.get(id));
    }

    //todo
    @Override
    public boolean findUserByName(String name) {
        try {
            return this.userDao.checkUserByName(name);
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public boolean findUserByNumberPhone(long phone) {
        try {
             this.userDao.getByNumberPhone(phone);
            return true;
        } catch (NoResultException e) {
            return false;
        }
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

    @Override
    @Transactional
    public void createUser(UserCreateDto userDto) {
        UserCreateDto savedUser = UserCreateMapper.mapUserDto(this.userDao.create(UserCreateMapper.mapCreateUser(userDto)));
        List<RoleDto> roles = RoleMapper.mapRolesDto(this.roleDao.getAll());
        savedUser.getRoles().add(roles.get(0));
        savedUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        savedUser.setStatus(Status.INACTIVE);
        savedUser.setCreatedDate(OffsetDateTime.now());
        this.userDao.update(UserCreateMapper.mapUser(savedUser));
        log.info("Created new user. {}", savedUser.getUsername());
        try {
            this.emailSender.sendEmailFromAdmin(UserCreateMapper.mapCreateUser(savedUser), Base64.getEncoder().encodeToString(savedUser.getEmail().getBytes()));
        } catch (Exception e) {
            log.error("Failed to send email. Error massage: {}", e.getMessage());
        }
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

    //todo autorization

    @Override
    @Transactional
    public void updateUser(UserUpdateDto userUpdateDto) {
        User entity = this.userDao.get(userUpdateDto.getId());
        entity.setUsername(userUpdateDto.getUsername());
        entity.setFirstName(userUpdateDto.getFirstName());
        entity.setLastName(userUpdateDto.getLastName());
        entity.setEmail(userUpdateDto.getEmail());
        entity.setPhone(userUpdateDto.getPhone());
        this.userDao.update(entity);
        Collection<SimpleGrantedAuthority> nowAuthorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder
                .getContext().getAuthentication().getAuthorities();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(entity.getUsername(), entity.getPassword(), nowAuthorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public boolean findUserByEmail(String email) {
        try {
            this.userDao.getUserByEmail(email);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean createNewPassword(EmailDto email) {
        try {
            User user = this.userDao.getUserByEmail(email.getEmail());
            String newPassword = RandomStringUtils.random(10, 0, 8, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
            user.setPassword(passwordEncoder.encode(newPassword));
            this.userDao.update(user);
            this.emailSender.sendEmailWithNewPasswordFromAdmin(user, newPassword);
            return true;
        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            log.error("Failed to send email. Error massage: {}", e.getMessage());
            return true;
        }
    }
}
