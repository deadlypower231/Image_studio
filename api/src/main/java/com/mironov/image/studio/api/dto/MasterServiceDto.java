package com.mironov.image.studio.api.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasterServiceDto {

    private long id;
    private long idMaster;
    private String name;
    private double price;
    private UserDto user;

    @Override
    public String toString() {
        return name;
    }
}
