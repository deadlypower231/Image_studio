package com.mironov.image.studio.api.dao;

import com.mironov.image.studio.entities.User;

import java.util.List;

public interface IUserDao extends IAGenericDao<User>{
    User getByName(String name);
    List<User> getAll();
}
