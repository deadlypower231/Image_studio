package com.mironov.image.studio.api.dto;

import com.mironov.image.studio.enums.Status;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleDto {

    private long id;
    private String time;
    private UserDto user;
    private Status status;

}
