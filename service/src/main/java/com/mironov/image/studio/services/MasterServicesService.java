package com.mironov.image.studio.services;

import com.mironov.image.studio.api.dao.IMasterServiceDao;
import com.mironov.image.studio.api.dao.IUserDao;
import com.mironov.image.studio.api.dto.MasterServiceDto;
import com.mironov.image.studio.api.dto.ServiceIdsDto;
import com.mironov.image.studio.api.mappers.MasterServiceMapper;
import com.mironov.image.studio.api.mappers.UserMapper;
import com.mironov.image.studio.api.services.IMasterServicesService;
import com.mironov.image.studio.entities.MasterService;
import com.mironov.image.studio.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MasterServicesService implements IMasterServicesService {

    private final IMasterServiceDao masterServiceDao;
    private final IUserDao userDao;

    public MasterServicesService(IMasterServiceDao masterServiceDao, IUserDao userDao) {
        this.masterServiceDao = masterServiceDao;
        this.userDao = userDao;
    }

    @Override
    public List<MasterServiceDto> getAll() {
        return MasterServiceMapper.mapMasterServicesDto(this.masterServiceDao.getAll());
    }

    @Override
    @Transactional
    public void createService(MasterServiceDto masterServiceDto, long idMaster) {
        User user = this.userDao.get(idMaster);
        masterServiceDto.setUser(UserMapper.mapUserDto(user));
        this.masterServiceDao.create(MasterServiceMapper.mapMasterServiceCreate(masterServiceDto));
    }

    @Override
    @Transactional
    public void deleteService(Long idService) {
        this.masterServiceDao.delete(this.masterServiceDao.get(idService));
    }

    @Override
    @Transactional
    public void deleteServices(ServiceIdsDto serviceIdsDto) {
        List<MasterService> services = serviceIdsDto.getIds().stream().map(this.masterServiceDao::get).collect(Collectors.toList());
        for (MasterService s : services) {
            this.masterServiceDao.delete(s);
        }
    }

}
