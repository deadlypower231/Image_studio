package com.mironov.image.studio.services;

import com.mironov.image.studio.api.dao.*;
import com.mironov.image.studio.api.dto.IdDataOrderDto;
import com.mironov.image.studio.api.dto.OrderDto;
import com.mironov.image.studio.api.mappers.OrderMapper;
import com.mironov.image.studio.api.services.IOrderService;
import com.mironov.image.studio.api.utils.IEmailSender;
import com.mironov.image.studio.entities.Order;
import com.mironov.image.studio.entities.Schedule;
import com.mironov.image.studio.entities.User;
import com.mironov.image.studio.enums.Status;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class OrderService implements IOrderService {

    private final IOrderDao orderDao;
    private final IUserDao userDao;
    private final IMasterServiceDao masterServiceDao;
    private final IScheduleDao scheduleDao;
    private final ITournamentDao tournamentDao;
    private final IEmailSender emailSender;

    public OrderService(IOrderDao orderDao, IUserDao userDao, IMasterServiceDao masterServiceDao, IScheduleDao scheduleDao, ITournamentDao tournamentDao, IEmailSender emailSender) {
        this.orderDao = orderDao;
        this.userDao = userDao;
        this.masterServiceDao = masterServiceDao;
        this.scheduleDao = scheduleDao;
        this.tournamentDao = tournamentDao;
        this.emailSender = emailSender;
    }

    @Override
    @Transactional
    public void createOrder(IdDataOrderDto idDataOrderDto, long id) {
        Order order = new Order();
        User currentUser = this.userDao.get(id);
        order.setMasterService(this.masterServiceDao.get(idDataOrderDto.getIdService()));
        order.setMaster(this.userDao.get(idDataOrderDto.getIdMaster()));
        Schedule schedule = this.scheduleDao.get(idDataOrderDto.getIdSchedule());
        schedule.setStatus(Status.INACTIVE);
        schedule.setUser(currentUser);
        order.setSchedule(schedule);
        order.setTournament(this.tournamentDao.get(idDataOrderDto.getIdTournament()));
        order.setPrice(order.getMasterService().getPrice());
        Order saved = this.orderDao.create(order);
        List<Order> orders = new ArrayList<>(currentUser.getOrders());
        orders.add(saved);
        currentUser.setOrders(orders);
        this.userDao.update(currentUser);
        log.info("A new order has been created for the user: {}", currentUser.getUsername());
        try {
            this.emailSender.sendEmailFromAdminByOrder(currentUser, order);
        } catch (Exception e) {
            log.error("Failed to send email to {}. Error massage: {}", currentUser.getUsername(), e.getMessage());
        }

    }

    @Override
    public List<OrderDto> getAllOrdersByCurrentUser(long id) {
        return OrderMapper.mapOrdersDto(this.orderDao.getAllByCurrentUser(id));
    }

    @Override
    @Transactional
    public void deleteOrder(long id) {
        Order order = this.orderDao.get(id);
        this.orderDao.delete(order);
        Schedule schedule = this.scheduleDao.get(order.getSchedule().getId());
        schedule.setStatus(Status.ACTIVE);
        this.scheduleDao.update(schedule);
    }

}
