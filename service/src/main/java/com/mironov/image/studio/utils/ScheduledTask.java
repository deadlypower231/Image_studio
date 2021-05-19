package com.mironov.image.studio.utils;

import com.mironov.image.studio.api.dao.IOrderDao;
import com.mironov.image.studio.api.dao.ITournamentDao;
import com.mironov.image.studio.api.services.IScheduleTask;
import com.mironov.image.studio.entities.Order;
import com.mironov.image.studio.entities.Tournament;
import com.mironov.image.studio.enums.Status;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Log4j2
@Component
public class ScheduledTask implements IScheduleTask {

    private final ITournamentDao tournamentDao;
    private final IOrderDao orderDao;

    public ScheduledTask(ITournamentDao tournamentDao, IOrderDao orderDao) {
        this.tournamentDao = tournamentDao;
        this.orderDao = orderDao;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    @Override
    public void checkTournamentDate() {
        List<Tournament> tournaments = this.tournamentDao.getAllIsActive();
        List<Order> orders = this.orderDao.getAllIsActive(Status.ACTIVE);
        tournaments.removeIf(x -> x.getDate().after(Date.valueOf(LocalDate.now())));
        for (Tournament x : tournaments) {
            x.setStatus(Status.INACTIVE);
            this.tournamentDao.update(x);
            for (Order o : orders) {
                if(o.getTournament().getId() == x.getId()){
                    o.setStatus(Status.INACTIVE);
                    this.orderDao.update(o);
                }
            }
            log.info("Tournament {} is inactive.", x.getName());
        }
        if (tournaments.isEmpty()) {
            log.info("Scheduled task is successful!");
        }
    }
}
