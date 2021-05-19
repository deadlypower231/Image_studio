package com.mironov.image.studio.api.services;

import com.mironov.image.studio.api.dto.MasterServiceDto;
import com.mironov.image.studio.api.dto.ServiceIdsDto;

import java.util.List;

public interface IMasterServicesService {

    List<MasterServiceDto> getAll();

    void createService(MasterServiceDto masterServiceDto, long idMaster);

    void deleteService(Long idService);

    void deleteServices(ServiceIdsDto serviceIdsDto);

}
