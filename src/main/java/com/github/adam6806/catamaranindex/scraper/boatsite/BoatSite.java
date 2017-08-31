package com.github.adam6806.catamaranindex.scraper.boatsite;

import com.github.adam6806.catamaranindex.database.model.BoatEntity;

import java.util.List;

public interface BoatSite {

    List<BoatEntity> getBoatEntities();
}
