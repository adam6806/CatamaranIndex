package com.github.adam6806.catamaranindex.database.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceInterface<T> {

    void save(T entity);

    void update(T entity);

    void delete(T entity);

    void deleteAll();

    T findById(int id);

    List<T> findAll();
}
