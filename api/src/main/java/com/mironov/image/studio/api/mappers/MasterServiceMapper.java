package com.mironov.image.studio.api.mappers;

import com.mironov.image.studio.api.dto.MasterServiceDto;
import com.mironov.image.studio.api.dto.UserDto;
import com.mironov.image.studio.entities.MasterService;
import com.mironov.image.studio.entities.User;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@UtilityClass
public class MasterServiceMapper {

    public MasterService mapMasterService(MasterServiceDto source) {
        return MasterService.builder()
                .id(source.getId())
                .name(source.getName())
                .price(source.getPrice())
                .user(Optional.ofNullable(mapUser(source.getUser())).orElse(null))
                .build();
    }

    public MasterServiceDto mapMasterServiceDto(MasterService source) {
        return MasterServiceDto.builder()
                .id(source.getId())
                .name(source.getName())
                .price(source.getPrice())
                .user(Optional.ofNullable(mapUserDto(source.getUser())).orElse(null))
                .build();
    }

    public MasterService mapMasterServiceCreate(MasterServiceDto source) {
        return MasterService.builder()
                .name(source.getName())
                .price(source.getPrice())
                .user(Optional.ofNullable(mapUser(source.getUser())).orElse(null))
                .build();
    }

    public List<MasterService> mapMasterServices(List<MasterServiceDto> source) {
        return source.stream().map(MasterServiceMapper::mapMasterService).collect(Collectors.toList());
    }

    public List<MasterServiceDto> mapMasterServicesDto(List<MasterService> source) {
        return source.stream().map(MasterServiceMapper::mapMasterServiceDto).collect(Collectors.toList());
    }

    private User mapUser(UserDto source){
        return User.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .username(source.getUsername())
                .phone(source.getPhone())
                .description(source.getDescription())
                .build();
    }
    private UserDto mapUserDto(User source){
        return UserDto.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .email(source.getEmail())
                .username(source.getUsername())
                .phone(source.getPhone())
                .description(source.getDescription())
                .build();
    }

}
