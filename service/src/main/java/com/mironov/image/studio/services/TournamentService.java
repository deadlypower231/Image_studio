package com.mironov.image.studio.services;

import com.mironov.image.studio.api.dao.ITournamentDao;
import com.mironov.image.studio.api.dto.TournamentDto;
import com.mironov.image.studio.api.mappers.TournamentMapper;
import com.mironov.image.studio.api.services.ITournamentService;
import com.mironov.image.studio.entities.Tournament;
import com.mironov.image.studio.utils.LogoFileUploader;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Log4j2
public class TournamentService implements ITournamentService {

    private final ITournamentDao tournamentDao;

    public TournamentService(ITournamentDao tournamentDao) {
        this.tournamentDao = tournamentDao;
    }

    @Override
    public List<TournamentDto> getAll() {
        return TournamentMapper.mapTournamentsDto(this.tournamentDao.getAll());
    }

    @Transactional
    public void saveTournament(TournamentDto tournamentDto, MultipartFile file) {
        Tournament savedTournament = this.tournamentDao.create(TournamentMapper.mapTournamentCreate(tournamentDto));
//        Description description = tournamentDto.getDescription();
//        savedTournament.setDescription(description);
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
}
