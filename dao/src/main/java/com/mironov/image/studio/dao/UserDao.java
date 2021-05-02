package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.IUserDao;
import com.mironov.image.studio.entities.*;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.*;

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

    @Override
    public List<User> getAllMasters() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        Join<User, Role> roles = root.join(User_.ROLES);
        criteriaQuery
                .select(root)
                .where(criteriaBuilder.equal(roles.get(Role_.ROLE_NAME), "ROLE_MASTER"))
                .orderBy(criteriaBuilder.asc(root.get(User_.USERNAME)));
        TypedQuery<User> q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }

    @Override
    public List<User> searchUsers(String text) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> users = criteriaQuery.from(User.class);
        Predicate predicate = criteriaBuilder.or(
                criteriaBuilder.like(criteriaBuilder.lower(users.get(User_.firstName)), "%" + text.toLowerCase(Locale.ROOT) + "%"),
                criteriaBuilder.like(criteriaBuilder.lower(users.get(User_.lastName)), "%" + text.toLowerCase(Locale.ROOT) + "%"),
                criteriaBuilder.like(criteriaBuilder.lower(users.get(User_.username)), "%" + text.toLowerCase(Locale.ROOT) + "%"));
        criteriaQuery
                .select(users)
                .where(predicate);
        TypedQuery<User> q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }

    @Override
    public Set<User> searchMasters(List<String> strings) {
        Set<User> result = new HashSet<>();
        for (int i = 0; i <strings.size() ; i++) {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> users = criteriaQuery.from(User.class);
            Join<User, Role> roles = users.join(User_.ROLES);
            Predicate predicate = criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(users.get(User_.firstName)), "%" + strings.get(i).toLowerCase(Locale.ROOT) + "%"),
                    criteriaBuilder.like(criteriaBuilder.lower(users.get(User_.lastName)), "%" + strings.get(i).toLowerCase(Locale.ROOT) + "%"),
                    criteriaBuilder.like(criteriaBuilder.lower(users.get(User_.username)), "%" + strings.get(i).toLowerCase(Locale.ROOT) + "%"));
            Predicate resultPredicate = criteriaBuilder.and(
                    predicate,
                    criteriaBuilder.equal(roles.get(Role_.ROLE_NAME), "ROLE_MASTER"));
            criteriaQuery
                    .select(users)
                    .where(resultPredicate);
            TypedQuery<User> q = entityManager.createQuery(criteriaQuery);
            result.addAll(q.getResultList());
        }
        return result;
    }

}
