package com.mironov.image.studio.api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderForMasterDto {

    private TournamentDto tournament;
    private List<ScheduleDto> schedules;

}
