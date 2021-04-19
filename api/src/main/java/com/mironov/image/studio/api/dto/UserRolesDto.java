package com.mironov.image.studio.api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRolesDto {
    private Long idUser;
    private List<String> roles;
}
