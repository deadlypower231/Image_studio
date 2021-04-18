package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.IOrderDao;
import com.mironov.image.studio.entities.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDao extends AGenericDao<Order> implements IOrderDao {
    public OrderDao(){
        super(Order.class);
    }
}
