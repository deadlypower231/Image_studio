package com.mironov.image.studio.api.dao;

import com.mironov.image.studio.entities.Order;
import com.mironov.image.studio.enums.Status;

import java.util.List;

public interface IOrderDao extends IAGenericDao<Order> {

    List<Order> getAllByCurrentUser(long id, Status status);

    List<Order> getAllIsActive(Status status);

}
