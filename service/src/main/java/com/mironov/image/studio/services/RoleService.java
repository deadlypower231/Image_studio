package com.mironov.image.studio.services;

import com.mironov.image.studio.api.dao.IRoleDao;
import com.mironov.image.studio.api.dto.RoleDto;
import com.mironov.image.studio.api.mappers.RoleMapper;
import com.mironov.image.studio.api.services.IRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    private final IRoleDao roleDao;

    public RoleService(IRoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public List<RoleDto> getAll() {
        return RoleMapper.mapRolesDto(this.roleDao.getAll());
    }
}
