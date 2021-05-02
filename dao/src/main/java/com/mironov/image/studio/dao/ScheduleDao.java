package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.IScheduleDao;
import com.mironov.image.studio.entities.Schedule;
import com.mironov.image.studio.entities.Schedule_;
import com.mironov.image.studio.entities.User;
import com.mironov.image.studio.entities.User_;
import com.mironov.image.studio.enums.State;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class ScheduleDao extends AGenericDao<Schedule> implements IScheduleDao {
    public ScheduleDao(){
        super(Schedule.class);
    }

    @Override
    public List<Schedule> getSchedulesByIdTournamentIdMasterIsActive(long idTournament, long idMaster) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Schedule> criteria = criteriaBuilder.createQuery(Schedule.class);
        Root<User> root = criteria.from(User.class);
        Join<User, Schedule> schedules = root.join(User_.SCHEDULES);
        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(root.get(User_.ID),idMaster),
                criteriaBuilder.equal(schedules.get(Schedule_.TOURNAMENT), idTournament),
                criteriaBuilder.equal(schedules.get(Schedule_.STATE), State.ACTIVE));
        criteria.select(schedules)
                .where(predicate)
                .orderBy(criteriaBuilder.asc(schedules.get(Schedule_.TIME)));
        TypedQuery<Schedule> q = entityManager.createQuery(criteria);
        return q.getResultList();
    }

    @Override
    public List<Schedule> getSchedulesByIdTournamentIdMaster(long idTournament, long idMaster) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Schedule> criteria = criteriaBuilder.createQuery(Schedule.class);
        Root<User> root = criteria.from(User.class);
        Join<User, Schedule> schedules = root.join(User_.SCHEDULES);
        Predicate predicate = criteriaBuilder.and(
                criteriaBuilder.equal(root.get(User_.ID),idMaster),
                criteriaBuilder.equal(schedules.get(Schedule_.TOURNAMENT), idTournament));
        criteria.select(schedules)
                .where(predicate)
                .orderBy(criteriaBuilder.asc(schedules.get(Schedule_.TIME)));
        TypedQuery<Schedule> q = entityManager.createQuery(criteria);
        return q.getResultList();
    }

}
