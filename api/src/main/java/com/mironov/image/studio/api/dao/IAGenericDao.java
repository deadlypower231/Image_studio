package com.mironov.image.studio.api.dao;

import com.mironov.image.studio.entities.AEntity;

public interface IAGenericDao<T extends AEntity<Long>>{
    T create(T entity);
    T get(Long id);
    void update(T entity);
    void delete(T entity);
}
