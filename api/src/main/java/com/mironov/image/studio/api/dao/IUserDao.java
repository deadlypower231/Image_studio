package com.mironov.image.studio.api.dao;

import com.mironov.image.studio.entities.User;

import java.util.List;
import java.util.Set;

public interface IUserDao extends IAGenericDao<User> {

    boolean checkUserByName(String name);

    boolean checkUserByEmail(String email);

    boolean checkUserByPhone(String phone);

    User getByName(String name);

    User getUserByEmail(String email);

    List<User> getAll();

    List<User> getAllMasters();

    List<User> searchUsers(String text);

    Set<User> searchMasters(List<String> strings);

}
