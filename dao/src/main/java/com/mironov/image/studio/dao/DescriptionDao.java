package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.IDescriptionDao;
import com.mironov.image.studio.entities.Description;
import org.springframework.stereotype.Repository;

@Repository
public class DescriptionDao extends AGenericDao<Description> implements IDescriptionDao {
    public DescriptionDao() {
        super(Description.class);
    }
}
