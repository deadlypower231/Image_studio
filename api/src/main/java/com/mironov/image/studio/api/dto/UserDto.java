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
    @Size(min = 3, max = 15, message = "Имя должно быть от 3 до 15 символов")
    private String username;
    @NotBlank(message = "Не должно быть пустым")
    @Size(min = 5, max = 100, message = "Имэйл должен быть от 5 до 100 символов")
    private String email;
    @NotBlank(message = "Не должно быть пустым")
    @Size(min = 4, max = 25, message = "Введите пароль от 4 до 25 символов")
    private String password;
//    @Size(min = 1, max = 11, message = "Введите 11 значный номер телефона")
    private long phone;
    @NotBlank(message = "Не должно быть пустым")
    @Size(min = 2, max = 15, message = "Введите имя от 2 до 15 символов")
    private String firstName;
    @NotBlank(message = "Не должно быть пустым")
    @Size(min = 2, max = 15, message = "Введите имя от 2 до 15 символов")
    private String lastName;
    private Description description;
    private List<Role> roles;
    private List<Tournament> tournaments;
    private List<Schedule> schedules;
    private List<Order> orders;
}

