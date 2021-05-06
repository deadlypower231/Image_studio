package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.IRoleDao;
import com.mironov.image.studio.entities.Role;
import com.mironov.image.studio.entities.Role_;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Locale;

@Repository
public class RoleDao extends AGenericDao<Role> implements IRoleDao {

    public RoleDao() {
        super(Role.class);
    }

}
