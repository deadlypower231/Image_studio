package com.mironov.image.studio.api.dto;

import com.mironov.image.studio.entities.*;
import com.mironov.image.studio.enums.Status;
import lombok.*;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private long id;
    private String username;
    private String email;
    private long phone;
    private String firstName;
    private String lastName;
    private Status status;
    private List<RoleDto> roles;
    private Description description;
    private List<TournamentDto> tournaments;
    private List<ScheduleDto> schedules;
    private List<OrderDto> orders;
    private List<MasterServiceDto> masterServices;
}

