package com.mironov.image.studio.api.mappers;

import com.mironov.image.studio.api.dto.ScheduleDto;
import com.mironov.image.studio.api.dto.UserDto;
import com.mironov.image.studio.entities.Schedule;
import com.mironov.image.studio.entities.User;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ScheduleMapper {

    public Schedule mapSchedule(ScheduleDto source) {
        return Schedule.builder()
                .id(source.getId())
                .time(source.getTime())
                .user(mapUser(source.getUser()))
                .status(source.getStatus())
                .build();
    }

    public ScheduleDto mapScheduleDto(Schedule source) {
        return ScheduleDto.builder()
                .id(source.getId())
                .time(source.getTime())
                .user(mapUserDto(source.getUser()))
                .status(source.getStatus())
                .build();
    }

    public List<ScheduleDto> mapSchedulesDto(List<Schedule> source) {
        return source.stream().map(ScheduleMapper::mapScheduleDto).collect(Collectors.toList());
    }

    private User mapUser(UserDto source) {
        if (source == null) {
            return null;
        }
        return User.builder()
                .id(source.getId())
                .username(source.getUsername())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .phone(source.getPhone())
                .build();
    }

    private UserDto mapUserDto(User source) {
        if (source == null) {
            return null;
        }
        return UserDto.builder()
                .id(source.getId())
                .username(source.getUsername())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .phone(source.getPhone())
                .build();
    }

}
