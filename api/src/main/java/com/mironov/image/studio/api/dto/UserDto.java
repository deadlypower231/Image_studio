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
    @Size(min = 3, max = 15, message = "Логин должен быть от 3 до 15 символов")
    private String username;
    @Size(min = 5, max = 100, message = "Имэйл должен быть от 5 до 100 символов")
    private String email;
    @Pattern(regexp = "\\+3[0-9]{11}", message = "Телефонный номер должен начинаться с +3, затем - 11 цифр")
    private String phone;
    @Size(min = 2, max = 15, message = "Введите имя от 2 до 15 символов")
    private String firstName;
    @Size(min = 2, max = 15, message = "Введите имя от 2 до 15 символов")
    private String lastName;
    private Status status;
    private List<RoleDto> roles;
    private Description description;
    private List<TournamentDto> tournaments;
    private List<ScheduleDto> schedules;
    private List<OrderDto> orders;
    private List<MasterServiceDto> masterServices;
}

