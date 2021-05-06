package com.mironov.image.studio.api.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmailDto {

    @NotBlank(message = "Введите адрес электронной почты!")
    private String email;

}
