package com.mironov.image.studio.api.mappers;

import com.mironov.image.studio.api.dto.ScheduleDto;
import com.mironov.image.studio.entities.Schedule;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ScheduleMapper {

        public Schedule mapSchedule(ScheduleDto source) {
            return Schedule.builder()
                    .id(source.getId())
                    .time(source.getTime())
                    .build();
        }

        public ScheduleDto mapScheduleDto(Schedule source) {
            return ScheduleDto.builder()
                    .id(source.getId())
                    .time(source.getTime())
                    .build();
        }

        public Schedule mapScheduleCreate(ScheduleDto source) {
            return Schedule.builder()
                    .time(source.getTime())
                    .build();
        }

        public List<Schedule> mapSchedules(List<ScheduleDto> source) {
            return source.stream().map(com.mironov.image.studio.api.mappers.ScheduleMapper::mapSchedule).collect(Collectors.toList());
        }

        public List<ScheduleDto> mapSchedulesDto(List<Schedule> source) {
            return source.stream().map(com.mironov.image.studio.api.mappers.ScheduleMapper::mapScheduleDto).collect(Collectors.toList());
        }

    }
