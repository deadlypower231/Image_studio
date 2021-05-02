package com.mironov.image.studio.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DescriptionDto {

    private long id;
    private String shortDescription;
    private String fullDescription;

}
