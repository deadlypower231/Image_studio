package com.mironov.image.studio.api.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDto {

    @NotBlank(message = "Введите что нибудь!")
    private String text;

}
