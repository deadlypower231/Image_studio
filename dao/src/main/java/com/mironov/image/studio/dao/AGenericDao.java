package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.IAGenericDao;
import com.mironov.image.studio.entities.AEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

    public List<T> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(getGenericClass());
        Root<T> root = criteriaQuery.from(getGenericClass());
        criteriaQuery.select(root);
        TypedQuery<T> result = entityManager.createQuery(criteriaQuery);
        return result.getResultList();
    }

    public void update(T entity){
        entityManager.merge(entity);
        entityManager.flush();
    }

    public void delete(T entity){
        entityManager.remove(entity);
    }
}
