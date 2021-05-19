package com.mironov.image.studio.services;

import com.mironov.image.studio.api.dao.IUserDao;
import com.mironov.image.studio.api.dto.UserCreateDto;
import com.mironov.image.studio.api.mappers.UserCreateMapper;
import com.mironov.image.studio.api.services.IActivationService;
import com.mironov.image.studio.enums.Status;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.Base64;

@Service
@Log4j2
public class ActivationService implements IActivationService {

    private final IUserDao userDao;

    public ActivationService(IUserDao userDao) {
        this.userDao = userDao;
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
                log.info("User {} is successful activation.", user.getUsername());
            }
            return user;
        } catch (NoResultException e) {
            log.error("Failed activation user by email: {}", email);
            return null;
        }
    }
}
