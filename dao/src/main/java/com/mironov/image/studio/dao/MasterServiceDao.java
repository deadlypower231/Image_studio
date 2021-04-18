package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.IMasterServiceDao;
import com.mironov.image.studio.entities.MasterService;
import org.springframework.stereotype.Repository;

@Repository
public class MasterServiceDao extends AGenericDao<MasterService> implements IMasterServiceDao {

    public MasterServiceDao (){
        super(MasterService.class);
    }

}
