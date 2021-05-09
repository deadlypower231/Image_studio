package com.mironov.image.studio.api.dao;

import com.mironov.image.studio.entities.Tournament;

import java.util.List;

public interface ITournamentDao extends IAGenericDao<Tournament> {

    List<Tournament> getTournamentWithMaster(long id);

}
