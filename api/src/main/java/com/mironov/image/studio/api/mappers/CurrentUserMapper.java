package com.mironov.image.studio.api.mappers;

import com.mironov.image.studio.api.dto.CurrentUserDto;
import com.mironov.image.studio.entities.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CurrentUserMapper {

    public CurrentUserDto mapDto (User source) {
        return CurrentUserDto.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .build();
    }

}
