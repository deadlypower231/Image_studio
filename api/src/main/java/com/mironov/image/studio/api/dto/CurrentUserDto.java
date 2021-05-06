package com.mironov.image.studio.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrentUserDto {

    private long id;
    private String firstName;
    private String lastName;

}
