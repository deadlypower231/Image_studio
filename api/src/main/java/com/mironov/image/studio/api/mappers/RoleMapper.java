package com.mironov.image.studio.api.mappers;

import com.mironov.image.studio.api.dto.RoleDto;
import com.mironov.image.studio.entities.Role;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class RoleMapper {

    public Role mapRole(RoleDto source) {
        return Role.builder()
                .id(source.getId())
                .roleName(source.getRoleName())
                .build();
    }

    public RoleDto mapRoleDto(Role source) {
        return RoleDto.builder()
                .id(source.getId())
                .roleName(source.getRoleName())
                .build();
    }

    public Role mapRoleCreate(RoleDto source) {
        return Role.builder()
                .roleName(source.getRoleName())
                .build();
    }

    public List<Role> mapRoles(List<RoleDto> source) {
        return source.stream().map(RoleMapper::mapRole).collect(Collectors.toList());
    }

    public List<RoleDto> mapRolesDto(List<Role> source) {
        return source.stream().map(RoleMapper::mapRoleDto).collect(Collectors.toList());
    }

}
