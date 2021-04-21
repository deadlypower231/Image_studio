package com.mironov.image.studio.api.services;

import com.mironov.image.studio.api.dto.TournamentDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ITournamentService {
    List<TournamentDto> getAll();
    void saveTournament(TournamentDto tournamentDto, MultipartFile file);
}
