package com.mironov.image.studio.api.services;

import com.mironov.image.studio.api.dto.TimeDto;
import com.mironov.image.studio.api.dto.TournamentDto;
import com.mironov.image.studio.api.dto.IdUsersDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ITournamentService {
    List<TournamentDto> getAll();
    TournamentDto get(long id);
    void saveTournament(TournamentDto tournamentDto, MultipartFile file);
    void addMastersToTournament(Long id, IdUsersDto idUsersDto);
    void saveScheduleToMasterWithTournament(Long idTournament, Long idMaster, TimeDto timeDto);
    void deleteById(long id);
}