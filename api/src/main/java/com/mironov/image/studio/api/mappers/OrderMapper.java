package com.mironov.image.studio.api.mappers;

import com.mironov.image.studio.api.dto.*;
import com.mironov.image.studio.entities.*;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@UtilityClass
public class OrderMapper {

    public Order mapOrder(OrderDto source){
        return Order.builder()
                .id(source.getId())
                .submitDate(source.getSubmitDate())
                .tournament(mapTournament(source.getTournamentDto()))
                .masterService(mapMasterService(source.getMasterServiceDto()))
                .schedule(mapSchedule(source.getScheduleDto()))
                .master(mapUser(source.getMaster()))
                .build();
    }

    public OrderDto mapOrderDto(Order source){
        return OrderDto.builder()
                .id(source.getId())
                .submitDate(source.getSubmitDate())
                .tournamentDto(mapTournamentDto(source.getTournament()))
                .masterServiceDto(mapMasterServiceDto(source.getMasterService()))
                .scheduleDto(mapScheduleDto(source.getSchedule()))
                .master(mapUserDto(source.getMaster()))
                .build();
    }

    public List<Order> mapOrders (List<OrderDto> source){
        return source.stream().map(OrderMapper::mapOrder).collect(Collectors.toList());
    }

    public List<OrderDto> mapOrdersDto (List<Order> source){
        return source.stream().map(OrderMapper::mapOrderDto).collect(Collectors.toList());
    }

    private Tournament mapTournament (TournamentDto source){
        return Tournament.builder()
                .id(source.getId())
                .address(source.getAddress())
                .name(source.getName())
                .date(source.getDate())
                .description(mapDescription(source.getDescription()))
                .build();
    }

    private TournamentDto mapTournamentDto (Tournament source){
        return TournamentDto.builder()
                .id(source.getId())
                .address(source.getAddress())
                .name(source.getName())
                .date(source.getDate())
                .description(mapDescriptionDto(source.getDescription()))
                .build();
    }

    private Description mapDescription(DescriptionDto source){
        return Description.builder()
                .id(source.getId())
                .shortDescription(source.getShortDescription())
                .fullDescription(source.getFullDescription())
                .build();
    }

    private DescriptionDto mapDescriptionDto(Description source){
        return DescriptionDto.builder()
                .id(source.getId())
                .shortDescription(source.getShortDescription())
                .fullDescription(source.getFullDescription())
                .build();
    }

    private User mapUser(UserDto source){
        return User.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .username(source.getUsername())
                .phone(source.getPhone())
                .description(source.getDescription())
                .build();
    }
    private UserDto mapUserDto(User source){
        return UserDto.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .username(source.getUsername())
                .phone(source.getPhone())
                .description(source.getDescription())
                .build();
    }

    private MasterServiceDto mapMasterServiceDto(MasterService source) {
        return MasterServiceDto.builder()
                .id(source.getId())
                .name(source.getName())
                .price(source.getPrice())
                .user(Optional.ofNullable(mapUserDto(source.getUser())).orElse(null))
                .build();
    }

    private MasterService mapMasterService(MasterServiceDto source) {
        return MasterService.builder()
                .id(source.getId())
                .name(source.getName())
                .price(source.getPrice())
                .user(Optional.ofNullable(mapUser(source.getUser())).orElse(null))
                .build();
    }

    private Schedule mapSchedule(ScheduleDto source) {
        return Schedule.builder()
                .id(source.getId())
                .time(source.getTime())
                .build();
    }

    private ScheduleDto mapScheduleDto(Schedule source) {
        return ScheduleDto.builder()
                .id(source.getId())
                .time(source.getTime())
                .build();
    }


}
