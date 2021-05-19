package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.IOrderDao;
import com.mironov.image.studio.entities.*;
import com.mironov.image.studio.entities.Order;
import com.mironov.image.studio.enums.Status;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class OrderDao extends AGenericDao<Order> implements IOrderDao {
    public OrderDao() {
        super(Order.class);
    }

    @Override
    public List<Order> getAllByCurrentUser(long id, Status status) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<User> root = criteriaQuery.from(User.class);
        Join<User, Order> orders = root.join(User_.ORDERS);
        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(root.get(AEntity_.ID), id),
                criteriaBuilder.equal(orders.get(Order_.STATUS), status));
        criteriaQuery.select(orders)
                .where(predicate);
        TypedQuery<Order> q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }

    @Override
    public List<Order> getAllIsActive(Status status) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<Order> root = criteriaQuery.from(Order.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get(Order_.STATUS), status));
        TypedQuery<Order> q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }

}
