package com.mironov.image.studio.api.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchDto {

    @NotEmpty(message = "Введите что нибудь")
    private String text;

}
