package com.mironov.image.studio.api.services;

import com.mironov.image.studio.api.dto.IdDataOrderDto;
import com.mironov.image.studio.api.dto.UserDto;

public interface IOrderService {

    void createOrder(IdDataOrderDto idDataOrderDto, UserDto userDto);

}
