package com.mironov.image.studio.utils;

import com.mironov.image.studio.api.dao.ITournamentDao;
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
public class ScheduledTask {

    private final ITournamentDao tournamentDao;

    public ScheduledTask(ITournamentDao tournamentDao) {
        this.tournamentDao = tournamentDao;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void checkTournamentDate() {
        List<Tournament> tournaments = this.tournamentDao.getAllIsActive();
        tournaments.removeIf(x -> x.getDate().after(Date.valueOf(LocalDate.now())));
        for (Tournament x :
                tournaments) {
            x.setStatus(Status.INACTIVE);
            this.tournamentDao.update(x);
            log.info("Tournament {} is inactive.", x.getName());
        }
        if (tournaments.isEmpty()) {
            log.info("Scheduled task is successful!");
        }
    }
}
