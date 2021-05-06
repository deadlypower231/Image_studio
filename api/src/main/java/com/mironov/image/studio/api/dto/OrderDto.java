package com.mironov.image.studio.api.dto;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private long id;
    private OffsetDateTime submitDate;
    private TournamentDto tournamentDto;
    private MasterServiceDto masterServiceDto;
    private ScheduleDto scheduleDto;
    private UserDto master;
    private double price;

}
