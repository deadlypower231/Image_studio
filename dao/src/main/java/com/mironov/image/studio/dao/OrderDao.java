package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.IOrderDao;
import com.mironov.image.studio.entities.Order;
import com.mironov.image.studio.entities.User;
import com.mironov.image.studio.entities.User_;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class OrderDao extends AGenericDao<Order> implements IOrderDao {
    public OrderDao() {
        super(Order.class);
    }

    @Override
    public List<Order> getAllByCurrentUser(long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(Order.class);
        Root<User> root = criteriaQuery.from(User.class);
        Join<User, Order> orders = root.join(User_.ORDERS);
        criteriaQuery.select(orders)
                .where(criteriaBuilder.equal(root.get(User_.ID), id));
        TypedQuery<Order> q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }
}
