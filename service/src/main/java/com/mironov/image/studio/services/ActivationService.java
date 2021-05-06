package com.mironov.image.studio.services;

import com.mironov.image.studio.api.dao.IUserDao;
import com.mironov.image.studio.api.dto.UserCreateDto;
import com.mironov.image.studio.api.mappers.UserCreateMapper;
import com.mironov.image.studio.api.services.IActivationService;
import com.mironov.image.studio.api.services.ISecurityService;
import com.mironov.image.studio.enums.Status;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Base64;

@Service
public class ActivationService implements IActivationService {

    private final IUserDao userDao;
    private final ISecurityService securityService;

    public ActivationService(IUserDao userDao, ISecurityService securityService) {
        this.userDao = userDao;
        this.securityService = securityService;
    }

    @Override
    @Transactional
    public UserCreateDto activationUser(String email) {

        byte[] bytes = Base64.getDecoder().decode(email);
        String decodingEmail = new String(bytes);

        try {
            UserCreateDto user = UserCreateMapper.mapUserDto(this.userDao.getUserByEmail(decodingEmail));
            if (user.getStatus().getVal() == 0) {
                user.setStatus(Status.ACTIVE);
                this.userDao.update(UserCreateMapper.mapUser(user));
            }
            return user;
        } catch (NoResultException e) {
            return null;
        }
    }
}
