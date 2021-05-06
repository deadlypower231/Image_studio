package com.mironov.image.studio.services;

import com.mironov.image.studio.api.dao.*;
import com.mironov.image.studio.api.dto.IdDataOrderDto;
import com.mironov.image.studio.api.dto.OrderDto;
import com.mironov.image.studio.api.mappers.OrderMapper;
import com.mironov.image.studio.api.services.IOrderService;
import com.mironov.image.studio.entities.Order;
import com.mironov.image.studio.entities.Schedule;
import com.mironov.image.studio.entities.User;
import com.mironov.image.studio.enums.Status;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final IOrderDao orderDao;
    private final IUserDao userDao;
    private final IMasterServiceDao masterServiceDao;
    private final IScheduleDao scheduleDao;
    private final ITournamentDao tournamentDao;

    public OrderService(IOrderDao orderDao, IUserDao userDao, IMasterServiceDao masterServiceDao, IScheduleDao scheduleDao, ITournamentDao tournamentDao) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.masterServiceDao = masterServiceDao;
        this.scheduleDao = scheduleDao;
        this.tournamentDao = tournamentDao;
    }

    @Override
    @Transactional
    public void createOrder(IdDataOrderDto idDataOrderDto, long id) {
        Order order = new Order();
        order.setMasterService(this.masterServiceDao.get(idDataOrderDto.getIdService()));
        order.setMaster(this.userDao.get(idDataOrderDto.getIdMaster()));
        Schedule schedule = this.scheduleDao.get(idDataOrderDto.getIdSchedule());
        schedule.setStatus(Status.INACTIVE);
        order.setSchedule(schedule);
        order.setTournament(this.tournamentDao.get(idDataOrderDto.getIdTournament()));
        order.setPrice(order.getMasterService().getPrice());
        order.setSubmitDate(OffsetDateTime.now());
        Order saved = this.orderDao.create(order);
        User currentUser = this.userDao.get(id);
        List<Order> orders = new ArrayList<>(currentUser.getOrders());
        orders.add(saved);
        currentUser.setOrders(orders);
        this.userDao.update(currentUser);
    }

    @Override
    public List<OrderDto> getAllOrdersByCurrentUser(long id) {
        return OrderMapper.mapOrdersDto(this.orderDao.getAllByCurrentUser(id));
    }
}
