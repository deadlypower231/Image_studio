package com.mironov.image.studio.services;

import com.mironov.image.studio.api.dao.IScheduleDao;
import com.mironov.image.studio.api.dto.ScheduleDto;
import com.mironov.image.studio.api.mappers.ScheduleMapper;
import com.mironov.image.studio.api.services.IScheduleService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ScheduleService implements IScheduleService {

    private final IScheduleDao scheduleDao;

    public ScheduleService(IScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    @Override
    public List<ScheduleDto> getSchedulesByIdTournamentIdMasterIsActive(long idTournament, long idMaster) {
        return ScheduleMapper.mapSchedulesDto(this.scheduleDao.getSchedulesByIdTournamentIdMasterIsActive(idTournament, idMaster));
    }

    @Override
    public List<ScheduleDto> getSchedulesByIdTournamentIdMaster(long idTournament, long idMaster) {
        return ScheduleMapper.mapSchedulesDto(this.scheduleDao.getSchedulesByIdTournamentIdMaster(idTournament, idMaster));
    }

    @Override
    @Transactional
    public void deleteById(long id) {
        this.scheduleDao.delete(this.scheduleDao.get(id));
    }
}
