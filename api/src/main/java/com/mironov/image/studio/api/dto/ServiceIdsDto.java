package com.mironov.image.studio.api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceIdsDto {

    private List<Long> ids;

}
