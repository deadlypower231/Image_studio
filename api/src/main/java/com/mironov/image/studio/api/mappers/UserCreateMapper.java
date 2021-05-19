package com.mironov.image.studio.api.mappers;

import com.mironov.image.studio.api.dto.RoleDto;
import com.mironov.image.studio.api.dto.UserCreateDto;
import com.mironov.image.studio.entities.Role;
import com.mironov.image.studio.entities.User;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class UserCreateMapper {

    public UserCreateDto mapUserDto(User source) {
        return UserCreateDto.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .username(source.getUsername())
                .phone(source.getPhone())
                .email(source.getEmail())
                .status(source.getStatus())
                .password(source.getPassword())
                .roles(source.getRoles().stream().map(UserCreateMapper::mapRoleDto).collect(Collectors.toList()))
                .build();
    }

    public User mapUser(UserCreateDto source) {
        return User.builder()
                .id(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .username(source.getUsername())
                .phone(source.getPhone())
                .email(source.getEmail())
                .password(source.getPassword())
                .status(source.getStatus())
                .roles(source.getRoles().stream().map(UserCreateMapper::mapRole).collect(Collectors.toList()))
                .build();
    }

    public User mapCreateUser(UserCreateDto source) {
        return User.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .username(source.getUsername())
                .phone(source.getPhone())
                .email(source.getEmail())
                .password(source.getPassword())
                .status(source.getStatus())
                .build();
    }

    public RoleDto mapRoleDto(Role source) {
        return RoleDto.builder()
                .id(source.getId())
                .roleName(source.getRoleName())
                .build();
    }

    public Role mapRole(RoleDto source) {
        return Role.builder()
                .id(source.getId())
                .roleName(source.getRoleName())
                .build();
    }

}
