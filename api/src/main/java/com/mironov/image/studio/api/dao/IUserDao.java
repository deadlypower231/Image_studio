package com.mironov.image.studio.api.dao;

import com.mironov.image.studio.entities.User;

import java.util.List;
import java.util.Set;

public interface IUserDao extends IAGenericDao<User> {
    User getByName(String name);

    List<User> getAll();

    List<User> getAllMasters();

    List<User> searchUsers(String text);

    Set<User> searchMasters(List<String> strings);

}
