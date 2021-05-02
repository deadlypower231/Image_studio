package com.mironov.image.studio.api.dao;

import com.mironov.image.studio.entities.Schedule;

import java.util.List;

public interface IScheduleDao extends IAGenericDao<Schedule> {

    List<Schedule> getSchedulesByIdTournamentIdMasterIsActive(long idTournament, long idMaster);

    List<Schedule> getSchedulesByIdTournamentIdMaster(long idTournament, long idMaster);

}
