package com.mironov.image.studio.api.dao;

import com.mironov.image.studio.entities.AEntity;

import java.util.List;

public interface IAGenericDao<T extends AEntity<Long>> {
    
    T create(T entity);

    T get(Long id);

    List<T> getAll();

    void update(T entity);

    void delete(T entity);
    
}
