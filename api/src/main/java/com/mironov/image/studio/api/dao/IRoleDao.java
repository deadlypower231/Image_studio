package com.mironov.image.studio.api.dao;

import com.mironov.image.studio.entities.Role;

import java.util.List;

public interface IRoleDao extends IAGenericDao<Role> {

    List<Role> getAll();

}
