package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.IUserDao;
import com.mironov.image.studio.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao extends AGenericDao<User> implements IUserDao {
    public UserDao() {
        super(User.class);
    }
}
