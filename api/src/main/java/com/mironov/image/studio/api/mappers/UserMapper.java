package com.mironov.image.studio.api.mappers;

import com.mironov.image.studio.api.dto.OrderDto;
import com.mironov.image.studio.api.dto.RoleDto;
import com.mironov.image.studio.api.dto.ScheduleDto;
import com.mironov.image.studio.api.dto.UserDto;
import com.mironov.image.studio.entities.Order;
import com.mironov.image.studio.entities.Role;
import com.mironov.image.studio.entities.Schedule;
import com.mironov.image.studio.entities.User;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class UserMapper {
    public UserDto mapUserDto(User source) {
        return UserDto.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .username(source.getUsername())
                .phone(source.getPhone())
                .email(source.getEmail())
                .status(source.getStatus())
                .roles(source.getRoles().stream().map(UserMapper::mapRoleDto).collect(Collectors.toList()))
                .orders(source.getOrders().stream().map(UserMapper::mapOrderDto).collect(Collectors.toList()))
                .schedules(source.getSchedules().stream().map(UserMapper::mapScheduleDto).collect(Collectors.toList()))
                .description(source.getDescription())
                .masterServices(MasterServiceMapper.mapMasterServicesDto(source.getMasterServices()))
                .build();
    }

    public User mapUser(UserDto source) {
        return User.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .username(source.getUsername())
                .phone(source.getPhone())
                .email(source.getEmail())
                .status(source.getStatus())
                .roles(source.getRoles().stream().map(UserMapper::mapRole).collect(Collectors.toList()))
                .orders(source.getOrders().stream().map(UserMapper::mapOrder).collect(Collectors.toList()))
                .schedules(source.getSchedules().stream().map(UserMapper::mapSchedule).collect(Collectors.toList()))
                .description(source.getDescription())
                .masterServices(MasterServiceMapper.mapMasterServices(source.getMasterServices()))
                .build();
    }

    public List<User> mapUsers(List<UserDto> source) {
        return source.stream().map(UserMapper::mapUser).collect(Collectors.toList());
    }

    public List<UserDto> mapUsersDto(List<User> source) {
        return source.stream().map(UserMapper::mapUserDto).collect(Collectors.toList());
    }

    public Set<UserDto> mapUsersDto(Set<User> source) {
        return source.stream().map(UserMapper::mapUserDto).collect(Collectors.toSet());
    }

    private RoleDto mapRoleDto(Role source) {
        return RoleDto.builder()
                .id(source.getId())
                .roleName(source.getRoleName())
                .build();
    }

    private Role mapRole(RoleDto source) {
        return Role.builder()
                .id(source.getId())
                .roleName(source.getRoleName())
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

    private Order mapOrder(OrderDto source){
        return Order.builder()
                .id(source.getId())
                .submitDate(source.getSubmitDate())
                .build();
    }

    private OrderDto mapOrderDto(Order source){
        return OrderDto.builder()
                .id(source.getId())
                .submitDate(source.getSubmitDate())
                .build();
    }



}
