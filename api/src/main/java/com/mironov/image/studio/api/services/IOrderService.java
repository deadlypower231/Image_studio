package com.mironov.image.studio.api.services;

import com.mironov.image.studio.api.dto.IdDataOrderDto;
import com.mironov.image.studio.api.dto.OrderDto;

import java.util.List;

public interface IOrderService {

    void createOrder(IdDataOrderDto idDataOrderDto, long id);

    List<OrderDto> getAllOrdersByCurrentUser(long id);

    void deleteOrder(long id);

}
