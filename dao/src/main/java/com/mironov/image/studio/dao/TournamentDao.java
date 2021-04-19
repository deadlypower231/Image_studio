package com.mironov.image.studio.dao;

import com.mironov.image.studio.api.dao.ITournamentDao;
import com.mironov.image.studio.entities.Tournament;

public class TournamentDao extends AGenericDao<Tournament> implements ITournamentDao {
    public TournamentDao() {
        super(Tournament.class);
    }
}
