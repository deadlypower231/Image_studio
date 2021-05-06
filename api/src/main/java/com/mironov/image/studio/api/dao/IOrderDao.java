package com.mironov.image.studio.api.dao;

import com.mironov.image.studio.entities.Order;

import java.util.List;

public interface IOrderDao extends IAGenericDao<Order> {

    List<Order> getAllByCurrentUser(long id);

}
