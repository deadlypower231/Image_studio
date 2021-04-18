package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.IScheduleDao;
import com.mironov.image.studio.entities.Schedule;
import org.springframework.stereotype.Repository;

@Repository
public class ScheduleDao extends AGenericDao<Schedule> implements IScheduleDao {
    public ScheduleDao(){
        super(Schedule.class);
    }
}
