package com.mironov.image.studio.services;

import com.mironov.image.studio.api.dao.IDescriptionDao;
import com.mironov.image.studio.api.dao.IRoleDao;
import com.mironov.image.studio.api.dao.IUserDao;
import com.mironov.image.studio.api.dto.*;
import com.mironov.image.studio.api.mappers.DescriptionMapper;
import com.mironov.image.studio.api.mappers.RoleMapper;
import com.mironov.image.studio.api.mappers.UserCreateMapper;
import com.mironov.image.studio.api.mappers.UserMapper;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.api.services.IUserService;
import com.mironov.image.studio.api.utils.IEmailSender;
import com.mironov.image.studio.entities.Role;
import com.mironov.image.studio.entities.User;
import com.mironov.image.studio.enums.Status;
import com.mironov.image.studio.utils.LogoFileUploader;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.persistence.NoResultException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
public class UserService implements IUserService {

    private final PasswordEncoder passwordEncoder;
    private final IDescriptionDao descriptionDao;
    private final IUserDao userDao;
    private final IRoleDao roleDao;
    private final IEmailSender emailSender;
    private final ISecurityService securityService;

    public UserService(PasswordEncoder passwordEncoder, IDescriptionDao descriptionDao, IUserDao userDao, IRoleDao roleDao, IEmailSender emailSender, ISecurityService securityService) {
        this.passwordEncoder = passwordEncoder;
        this.descriptionDao = descriptionDao;
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.emailSender = emailSender;
        this.securityService = securityService;
    }

    @Override
    public UserDto getUser(long id) {
        return UserMapper.mapUserDto(this.userDao.get(id));
    }

    @Override
    public boolean findUserByName(String name) {
        try {
            return this.userDao.checkUserByName(name);
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public boolean findUserByNumberPhone(String phone) {
        try {
            return this.userDao.checkUserByPhone(phone);
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
        List<Role> rolesDB = this.roleDao.getAll().stream().filter(x -> userRolesDto.getRoles().toString().contains(x.toString())).collect(Collectors.toList());
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
        this.userDao.update(UserCreateMapper.mapUser(savedUser));
        log.info("Created new user: {}", savedUser.getUsername());
        try {
            this.emailSender.sendEmailFromAdmin(UserCreateMapper.mapCreateUser(savedUser), Base64.getEncoder().encodeToString(savedUser.getEmail().getBytes()));
        } catch (Exception e) {
            log.error("Failed to send email to {}. Error massage: {}", savedUser.getUsername(), e.getMessage());
        }
    }

    @Override
    public List<UserDto> getAllMasters() {
        return UserMapper.mapUsersDto(this.userDao.getAllMasters());
    }

    @Override
    public Set<UserDto> searchMasters(String text) {
        List<String> strings = Arrays.asList(text.split(" "));
        return UserMapper.mapUsersDto(this.userDao.searchMasters(strings));
    }

    @Override
    @Transactional
    public void updateUser(UserUpdateDto userUpdateDto) {
        User entity = this.userDao.get(userUpdateDto.getId());
        entity.setFirstName(userUpdateDto.getFirstName());
        entity.setLastName(userUpdateDto.getLastName());
        entity.setEmail(userUpdateDto.getEmail());
        entity.setPhone(userUpdateDto.getPhone());
        if (!entity.getUsername().equals(userUpdateDto.getUsername())) {
            entity.setUsername(userUpdateDto.getUsername());
            this.securityService.changePrincipal(entity);
        }
        this.userDao.update(entity);
    }

    @Override
    @Transactional
    public void updateUserDescription(DescriptionDto descriptionDto, long id) {
        User user = this.userDao.get(id);
        user.setDescription(this.descriptionDao.create(DescriptionMapper.mapCreateDescription(descriptionDto)));
        this.userDao.update(user);
    }

    @Override
    public void updateUserImage(MultipartFile multipartFile, long id) {
        User entity = this.userDao.get(id);
        try {
            if (multipartFile != null) {
                LogoFileUploader.createImageUser(multipartFile, entity);
            }
        } catch (IOException e) {
            log.error("Failed to upload image to user: {}. Error message: {}", entity.getUsername(), e.getMessage());
        }
    }

    @Override
    public boolean findUserByEmail(String email) {
        try {
            return this.userDao.checkUserByEmail(email);
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    public boolean checkValidEmail(String email) {
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
            return true;
        } catch (AddressException e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean createNewPassword(EmailDto email) {
        try {
            User user = this.userDao.getUserByEmail(email.getEmail());
            String newPassword = RandomStringUtils.random(10, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
            user.setPassword(passwordEncoder.encode(newPassword));
            this.userDao.update(user);
            this.emailSender.sendEmailWithNewPasswordFromAdmin(user, newPassword);
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    @Override
    @Transactional
    public void updateStatus(long id) {
        User user = this.userDao.get(id);
        user.setStatus((user.getStatus().getVal() == 1) ? Status.INACTIVE : Status.ACTIVE);
        this.userDao.update(user);
    }

    @Override
    public void sendMessage(CurrentUserDto user, SendMessageDto sendMessageDto) {
        this.emailSender.sendEmailFromMasterToUser(user, sendMessageDto);
    }

    @Override
    public Map<String, Object> findPaginatedUsers(Pageable pageable) {
        final List<UserDto> users = UserMapper.mapUsersDto(this.userDao.getAll());
        return new HashMap<>(pageable(pageable.getPageNumber(), pageable, users));
    }

    @Override
    public Map<String, Object> findPaginatedUsersSearch(Pageable pageable, String text) {
        final List<UserDto> users = UserMapper.mapUsersDto(this.userDao.searchUsers(text));
        return new HashMap<>(pageable(pageable.getPageNumber(), pageable, users));
    }

    private Map<String, Object> pageable(Integer currentPage, Pageable pageable, List<UserDto> users) {
        Map<String, Object> model = new HashMap<>();
        int startItem = pageable.getPageNumber() * pageable.getPageSize();
        List<UserDto> list;
        if (users.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageable.getPageSize(), users.size());
            list = users.subList(startItem, toIndex);
        }
        Page<UserDto> userPage = new PageImpl<>(list, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()), users.size());
        int totalPages = userPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.put("pageNumbers", pageNumbers);
        }
        model.put("previousPage", (currentPage > 1) ? currentPage : 1);
        model.put("nextPage", (currentPage + 1 < userPage.getTotalPages()) ? currentPage + 2 : userPage.getTotalPages());
        model.put("userPage", userPage);
        return model;
    }

}
