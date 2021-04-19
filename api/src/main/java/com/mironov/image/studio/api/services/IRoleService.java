package com.mironov.image.studio.api.services;

import com.mironov.image.studio.api.dto.RoleDto;

import java.util.List;

public interface IRoleService {
    List<RoleDto> getAll();
}
