package com.github.adam6806.catamaranindex.database.service;

import com.github.adam6806.catamaranindex.database.dao.BoatDAO;
import com.github.adam6806.catamaranindex.database.model.BoatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class BoatService implements ServiceInterface<BoatEntity> {

    @Autowired
    private BoatDAO boatDAO;

    @Override
    public void save(BoatEntity entity) {
        boatDAO.save(entity);
    }

    @Override
    public void update(BoatEntity entity) {
        boatDAO.update(entity);
    }

    @Override
    public void delete(BoatEntity entity) {
        boatDAO.delete(entity);
    }

    @Override
    public void deleteAll() {
        boatDAO.deleteAll();
    }

    @Override
    public BoatEntity findById(int id) {

        BoatEntity entity = boatDAO.findById(id);
        return entity;
    }

    @Override
    public List<BoatEntity> findAll() {
        List<BoatEntity> entities = boatDAO.findAll();
        return entities;
    }

    public BoatEntity findByUrl(String url) {
        BoatEntity entity = boatDAO.findByUrl(url);
        return entity;
    }
}
