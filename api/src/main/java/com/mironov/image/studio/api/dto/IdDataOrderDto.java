package com.mironov.image.studio.api.dto;

import lombok.*;

import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IdDataOrderDto {

    private long idTournament;
    private long idMaster;
    @Min(value = 1, message = "Выберите время")
    private long idSchedule;
    @Min(value = 1, message = "Выберите услугу")
    private long idService;

    public IdDataOrderDto(long idTournament, long idMaster) {
        this.idTournament = idTournament;
        this.idMaster = idMaster;
    }
}
