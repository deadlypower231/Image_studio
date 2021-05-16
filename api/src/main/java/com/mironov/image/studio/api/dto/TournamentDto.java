package com.mironov.image.studio.api.dto;

import com.mironov.image.studio.entities.Description;
import com.mironov.image.studio.entities.User;
import com.mironov.image.studio.enums.Status;
import lombok.*;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TournamentDto {

    private long id;
    private String address;
    private String name;
    private String date;
    private Status status;
    private DescriptionDto description;
    private List<UserDto> users;

}
