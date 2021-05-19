package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.ITournamentDao;
import com.mironov.image.studio.entities.*;
import com.mironov.image.studio.enums.Status;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class TournamentDao extends AGenericDao<Tournament> implements ITournamentDao {
    public TournamentDao() {
        super(Tournament.class);
    }

    @Override
    public List<Tournament> getTournamentWithMaster(long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tournament> criteriaQuery = criteriaBuilder.createQuery(Tournament.class);
        Root<Tournament> root = criteriaQuery.from(Tournament.class);
        Join<Tournament, User> users = root.join(Tournament_.USERS);
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(users.get(AEntity_.ID), id));
        TypedQuery<Tournament> q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }

    @Override
    public List<Tournament> getTournamentWithMasterIsActive(long id, Status status) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tournament> criteriaQuery = criteriaBuilder.createQuery(Tournament.class);
        Root<Tournament> root = criteriaQuery.from(Tournament.class);
        Join<Tournament, User> users = root.join(Tournament_.USERS);
        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(users.get(AEntity_.ID), id),
                criteriaBuilder.equal(root.get(Tournament_.STATUS), status));
        criteriaQuery.select(root)
                .where(predicate);
        TypedQuery<Tournament> q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }

    @Override
    public List<Tournament> getAllIsActive() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tournament> criteriaQuery = criteriaBuilder.createQuery(Tournament.class);
        Root<Tournament> root = criteriaQuery.from(Tournament.class);
        criteriaQuery.select(root)
                .where(criteriaBuilder.equal(root.get(Tournament_.STATUS), Status.ACTIVE));
        TypedQuery<Tournament> q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }

}
