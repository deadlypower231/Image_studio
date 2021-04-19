package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.IUserDao;
import com.mironov.image.studio.entities.User;
import com.mironov.image.studio.entities.User_;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDao extends AGenericDao<User> implements IUserDao {
    public UserDao() {
        super(User.class);
    }

    @Override
    public User getByName(String name) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery
                .select(root)
                .where(criteriaBuilder.equal(root.get(User_.USERNAME), name));
        TypedQuery<User> q = entityManager.createQuery(criteriaQuery);
        return q.getSingleResult();
    }

}
