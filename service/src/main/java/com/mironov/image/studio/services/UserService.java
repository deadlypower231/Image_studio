package com.mironov.image.studio.services;

import com.mironov.image.studio.api.dao.IUserDao;
import com.mironov.image.studio.api.services.IUserService;
import com.mironov.image.studio.entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    private final IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    public User getUser (long id){
        return this.userDao.get(id);
    }

}
