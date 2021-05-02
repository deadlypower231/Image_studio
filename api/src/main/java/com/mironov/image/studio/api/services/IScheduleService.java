package com.mironov.image.studio.api.services;

import com.mironov.image.studio.api.dto.ScheduleDto;

import java.util.List;

public interface IScheduleService {

    List<ScheduleDto> getSchedulesByIdTournamentIdMasterIsActive(long idTournament, long idMaster);

    List<ScheduleDto> getSchedulesByIdTournamentIdMaster(long idTournament, long idMaster);

    void deleteById(long id);

}
