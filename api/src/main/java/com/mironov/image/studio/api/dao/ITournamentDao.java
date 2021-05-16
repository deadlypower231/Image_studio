package com.mironov.image.studio.api.dao;

import com.mironov.image.studio.entities.Tournament;
import com.mironov.image.studio.enums.Status;

import java.util.List;

public interface ITournamentDao extends IAGenericDao<Tournament> {

    List<Tournament> getTournamentWithMaster(long id);

    List<Tournament> getTournamentWithMasterIsActive(long id, Status status);

    List<Tournament> getAllIsActive();

}
