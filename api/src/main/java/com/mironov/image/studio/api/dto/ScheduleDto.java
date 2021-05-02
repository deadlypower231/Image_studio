package com.mironov.image.studio.api.dto;

import com.mironov.image.studio.entities.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleDto {

    private long id;
    private String time;
    private User user;

}
