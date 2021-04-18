package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.IAGenericDao;
import com.mironov.image.studio.entities.AEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AGenericDao<T extends AEntity<Long>> implements IAGenericDao<T> {

    protected Class<T> clazz;
    @PersistenceContext
    protected EntityManager entityManager;

    public AGenericDao(Class<T> clazz) {
        this.clazz = clazz;
    }
    public Class<T> getGenericClass() {
        return this.clazz;
    }

    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    public T get(Long id){
        return entityManager.find(getGenericClass(), id);
    }

    public void update(T entity){
        entityManager.merge(entity);
        entityManager.flush();
    }

    public void delete(T entity){
        entityManager.remove(entity);
    }
}
