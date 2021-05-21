package com.mironov.image.studio.api.mappers;

import com.mironov.image.studio.api.dto.DescriptionDto;
import com.mironov.image.studio.api.dto.TournamentDto;
import com.mironov.image.studio.entities.Description;
import com.mironov.image.studio.entities.Tournament;
import lombok.experimental.UtilityClass;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
                .status(source.getStatus())
                .description(mapDescription(source.getDescription()))
                .users(UserMapper.mapUsers(source.getUsers()))
                .build();
    }

    public TournamentDto mapTournamentDto(Tournament source) {
        return TournamentDto.builder()
                .id(source.getId())
                .address(source.getAddress())
                .date(source.getDate())
                .name(source.getName())
                .status(source.getStatus())
                .description(mapDescriptionDto(source.getDescription()))
                .users(UserMapper.mapUsersDto(source.getUsers()))
                .build();
    }

    public Tournament mapTournamentCreate(TournamentDto source) {
        return Tournament.builder()
                .address(source.getAddress())
//                .date(Date.valueOf(source.getDate()))
                .date(source.getDate())
                .name(source.getName())
                .status(source.getStatus())
                .description(mapDescription(source.getDescription()))
                .build();
    }

    public List<Tournament> mapTournaments(List<TournamentDto> source) {
        return source.stream().map(TournamentMapper::mapTournament).collect(Collectors.toList());
    }

    public List<TournamentDto> mapTournamentsDto(List<Tournament> source) {
        return source.stream().map(TournamentMapper::mapTournamentDto).collect(Collectors.toList());
    }

    private Description mapDescription(DescriptionDto source) {
        return Description.builder()
                .id(source.getId())
                .shortDescription(source.getShortDescription())
                .fullDescription(source.getFullDescription())
                .build();
    }

    private DescriptionDto mapDescriptionDto(Description source) {
        return DescriptionDto.builder()
                .id(source.getId())
                .shortDescription(source.getShortDescription())
                .fullDescription(source.getFullDescription())
                .build();
    }

}
