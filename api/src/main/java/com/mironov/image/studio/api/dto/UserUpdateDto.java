package com.mironov.image.studio.api.dto;

import com.mironov.image.studio.entities.*;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserUpdateDto {

    private long id;
    @Size(min = 3, max = 15, message = "Логин должен быть от 3 до 15 символов")
    private String username;
    @Size(min = 5, max = 100, message = "Имэйл должен быть от 5 до 100 символов")
    private String email;
//    @Size(min = 1, max = 11, message = "Введите 11 значный номер телефона")
    private long phone;
    @Size(min = 2, max = 15, message = "Введите имя от 2 до 15 символов")
    private String firstName;
    @Size(min = 2, max = 15, message = "Введите имя от 2 до 15 символов")
    private String lastName;
}

