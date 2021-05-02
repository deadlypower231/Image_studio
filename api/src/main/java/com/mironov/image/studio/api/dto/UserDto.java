package com.mironov.image.studio.api.dto;

import com.mironov.image.studio.entities.*;
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
    @NotBlank(message = "Не должно быть пустым")
    @Size(min = 3, max = 15, message = "Логин должен быть от 3 до 15 символов")
    private String username;
    @NotBlank(message = "Не должно быть пустым")
    @Size(min = 5, max = 100, message = "Имэйл должен быть от 5 до 100 символов")
    private String email;
//    @Size(min = 1, max = 11, message = "Введите 11 значный номер телефона")
    private long phone;
    @NotBlank(message = "Не должно быть пустым")
    @Size(min = 2, max = 15, message = "Введите имя от 2 до 15 символов")
    private String firstName;
    @NotBlank(message = "Не должно быть пустым")
    @Size(min = 2, max = 15, message = "Введите имя от 2 до 15 символов")
    private String lastName;
    private List<RoleDto> roles;
    private Description description;
    private List<TournamentDto> tournaments;
    private List<ScheduleDto> schedules;
    private List<OrderDto> orders;
    private List<MasterServiceDto> masterServices;
}

