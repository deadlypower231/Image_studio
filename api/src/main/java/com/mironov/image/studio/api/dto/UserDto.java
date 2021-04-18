package com.mironov.image.studio.api.dto;

import com.mironov.image.studio.entities.*;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private long id;
    @NotBlank(message = "Не должно быть пустым")
    @Size(min = 3, max = 15, message = "Name should be between 3 and 15 characters")
    private String username;
    @NotBlank(message = "Не должно быть пустым")
    private String email;
    private String password;
    private long phone;
    private String firstName;
    private String lastName;
    private Description description;
    private List<Role> roles;
    private List<Tournament> tournaments;
    private List<Schedule> schedules;
    private List<Order> orders;
}

