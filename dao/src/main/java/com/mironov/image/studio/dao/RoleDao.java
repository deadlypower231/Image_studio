package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.IRoleDao;
import com.mironov.image.studio.entities.Role;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDao extends AGenericDao<Role> implements IRoleDao {

    public RoleDao(){
        super(Role.class);
    }

}
