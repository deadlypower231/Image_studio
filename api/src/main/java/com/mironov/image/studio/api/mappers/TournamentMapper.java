package com.mironov.image.studio.api.mappers;

import com.mironov.image.studio.api.dto.TournamentDto;
import com.mironov.image.studio.entities.Tournament;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class TournamentMapper {

    public Tournament mapTournament(TournamentDto source) {
        return Tournament.builder()
                .id(source.getId())
                .address(source.getAddress())
                .date(source.getDate())
                .name(source.getName())
                .description(source.getDescription())
                .build();
    }

    public TournamentDto mapTournamentDto(Tournament source) {
        return TournamentDto.builder()
                .id(source.getId())
                .address(source.getAddress())
                .date(source.getDate())
                .name(source.getName())
                .description(source.getDescription())
                .build();
    }

    public Tournament mapTournamentCreate(TournamentDto source) {
        return Tournament.builder()
                .address(source.getAddress())
                .date(source.getDate())
                .name(source.getName())
                .description(source.getDescription())
                .build();
    }

    public List<Tournament> mapTournaments(List<TournamentDto> source) {
        return source.stream().map(TournamentMapper::mapTournament).collect(Collectors.toList());
    }

    public List<TournamentDto> mapTournamentsDto(List<Tournament> source) {
        return source.stream().map(TournamentMapper::mapTournamentDto).collect(Collectors.toList());
    }

}
