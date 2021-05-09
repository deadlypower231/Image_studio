package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.ITournamentDao;
import com.mironov.image.studio.entities.Tournament;
import com.mironov.image.studio.entities.Tournament_;
import com.mironov.image.studio.entities.User;
import com.mironov.image.studio.entities.User_;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
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
                .where(criteriaBuilder.equal(users.get(User_.ID), id));
        TypedQuery<Tournament> q = entityManager.createQuery(criteriaQuery);
        return q.getResultList();
    }
}
