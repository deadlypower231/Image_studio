package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.ITournamentDao;
import com.mironov.image.studio.entities.Tournament;
import org.springframework.stereotype.Repository;

@Repository
public class TournamentDao extends AGenericDao<Tournament> implements ITournamentDao {
    public TournamentDao() {
        super(Tournament.class);
    }
}
