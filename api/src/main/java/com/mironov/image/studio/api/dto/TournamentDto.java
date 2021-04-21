package com.mironov.image.studio.api.dto;

import com.mironov.image.studio.entities.Description;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TournamentDto {
    private long id;
    private String address;
    private String name;
    private String date;
    private Description description;
}
