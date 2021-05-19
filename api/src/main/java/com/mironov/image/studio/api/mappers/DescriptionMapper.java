package com.mironov.image.studio.api.mappers;

import com.mironov.image.studio.api.dto.DescriptionDto;
import com.mironov.image.studio.entities.Description;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DescriptionMapper {

    public Description mapDescription(DescriptionDto source){
        return Description.builder()
                .id(source.getId())
                .shortDescription(source.getShortDescription())
                .fullDescription(source.getFullDescription())
                .build();
    }

    public Description mapCreateDescription(DescriptionDto source){
        return Description.builder()
                .shortDescription(source.getShortDescription())
                .fullDescription(source.getFullDescription())
                .build();
    }

}
