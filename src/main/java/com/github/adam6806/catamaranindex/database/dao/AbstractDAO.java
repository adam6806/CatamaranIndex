package com.github.adam6806.catamaranindex.database.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class AbstractDAO<T> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected abstract void save(T entity);

    protected abstract void update(T entity);

    protected abstract void delete(T entity);

    protected abstract void deleteAll();

    protected abstract T findById(int id);

    protected abstract List<T> findAll();
}
