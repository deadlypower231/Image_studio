package com.mironov.image.studio.api.services;

import com.mironov.image.studio.api.dto.IdUsersDto;
import com.mironov.image.studio.api.dto.OrderForMasterDto;
import com.mironov.image.studio.api.dto.TimeDto;
import com.mironov.image.studio.api.dto.TournamentDto;
import com.mironov.image.studio.enums.Status;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ITournamentService {

    List<TournamentDto> getAll();

    List<TournamentDto> getAllIsActive();

    TournamentDto get(long id);

    void saveTournament(TournamentDto tournamentDto, MultipartFile file);

    void addMastersToTournament(Long id, IdUsersDto idUsersDto);

    void saveScheduleToMasterWithTournament(Long idTournament, Long idMaster, TimeDto timeDto);

    void deleteById(long id);

    List<OrderForMasterDto> getTournamentsWithMaster(long id, Status status);

}