package com.mironov.image.studio.api.dao;

import com.mironov.image.studio.entities.User;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Set;

public interface IUserDao extends IAGenericDao<User> {

    boolean checkUserByName(String name) throws NoResultException;

    User getByName(String name);

    User getByNumberPhone(long phone);

    User getUserByEmail(String email);

    List<User> getAll();

    List<User> getAllMasters();

    List<User> searchUsers(String text);

    Set<User> searchMasters(List<String> strings);
    
}
