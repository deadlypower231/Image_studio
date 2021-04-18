package com.mironov.image.studio.api.mappers;

import com.mironov.image.studio.api.dto.UserDto;
import com.mironov.image.studio.entities.User;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class UserMapper {
    public UserDto mapUserDto(User source){
        return UserDto.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .username(source.getUsername())
                .phone(source.getPhone())
                .email(source.getEmail())
                .password(source.getPassword())
                .roles(source.getRoles())
                .orders(source.getOrders())
                .schedules(source.getSchedules())
                .description(source.getDescription())
                .build();
    }

    public User mapUser(UserDto source){
        return User.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .username(source.getUsername())
                .phone(source.getPhone())
                .email(source.getEmail())
                .password(source.getPassword())
                .roles(source.getRoles())
                .orders(source.getOrders())
                .schedules(source.getSchedules())
                .description(source.getDescription())
                .build();
    }
    public User createMapUser(UserDto source){
        return User.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .username(source.getUsername())
                .phone(source.getPhone())
                .email(source.getEmail())
                .roles(source.getRoles())
                .password(source.getPassword())
                .build();
    }

    public List<User> mapUsers(List<UserDto> source){
        return source.stream().map(UserMapper::mapUser).collect(Collectors.toList());
    }

    public List<UserDto> mapUserDtos(List<User> source) {
        return source.stream().map(UserMapper::mapUserDto).collect(Collectors.toList());
    }
}
