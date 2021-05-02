package com.mironov.image.studio.services;

import com.mironov.image.studio.api.dao.IScheduleDao;
import com.mironov.image.studio.api.dao.ITournamentDao;
import com.mironov.image.studio.api.dao.IUserDao;
import com.mironov.image.studio.api.dto.IdUsersDto;
import com.mironov.image.studio.api.dto.ScheduleDto;
import com.mironov.image.studio.api.dto.TimeDto;
import com.mironov.image.studio.api.dto.TournamentDto;
import com.mironov.image.studio.api.mappers.ScheduleMapper;
import com.mironov.image.studio.api.mappers.TournamentMapper;
import com.mironov.image.studio.api.services.ITournamentService;
import com.mironov.image.studio.entities.Schedule;
import com.mironov.image.studio.entities.Tournament;
import com.mironov.image.studio.entities.User;
import com.mironov.image.studio.enums.State;
import com.mironov.image.studio.utils.LogoFileUploader;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Log4j2
public class TournamentService implements ITournamentService {

    private final ITournamentDao tournamentDao;
    private final IScheduleDao scheduleDao;
    private final IUserDao userDao;

    public TournamentService(ITournamentDao tournamentDao, IScheduleDao scheduleDao, IUserDao userDao) {
        this.tournamentDao = tournamentDao;
        this.scheduleDao = scheduleDao;
        this.userDao = userDao;
    }

    @Override
    public TournamentDto get(long id) {
        return TournamentMapper.mapTournamentDto(this.tournamentDao.get(id));
    }

    @Override
    public List<TournamentDto> getAll() {
        return TournamentMapper.mapTournamentsDto(this.tournamentDao.getAll());
    }


    @Transactional
    public void saveTournament(TournamentDto tournamentDto, MultipartFile file) {
        Tournament savedTournament = this.tournamentDao.create(TournamentMapper.mapTournamentCreate(tournamentDto));
        try {
            if (file.isEmpty()) {
                LogoFileUploader.defaultLogoTournament(savedTournament);
            } else {
                LogoFileUploader.createLogoTournament(file, savedTournament);
            }
        } catch (IOException e) {
            log.error("Failed to upload image. Error message: {}", e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        this.tournamentDao.delete(this.tournamentDao.get(id));
    }

    @Transactional
    public void addMastersToTournament(Long id, IdUsersDto idUsersDto) {
        Tournament tournament = this.tournamentDao.get(id);
        List<User> list = new ArrayList<>();
        for (int i = 0; i < idUsersDto.getIds().size(); i++) {
            list.add(this.userDao.get(idUsersDto.getIds().get(i)));
        }
        tournament.setUsers(list);
        this.tournamentDao.update(tournament);
    }

    @Transactional
    public void saveScheduleToMasterWithTournament(Long idTournament, Long idMaster, TimeDto timeDto) {
        Schedule schedule = new Schedule();
        schedule.setTime(timeDto.getTime());
        schedule.setTournament(this.tournamentDao.get(idTournament));
        schedule.setState(State.ACTIVE);
        Schedule savedSchedule = this.scheduleDao.create(schedule);
        User user = this.userDao.get(idMaster);
        user.getSchedules().add(savedSchedule);
    }
}
