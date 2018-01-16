package com.demianenko.application.model.dao.interfaces;

import java.io.Serializable;
import java.util.List;

/**
 * An interface that represents a generic data access object
 * @param <T>  type of the target entity
 * @param <PK> primary key by which the target entity can be retrieved
 */
public interface IGenericDao<T, PK extends Serializable> {
    PK add(T t);

    T find(PK id);

    void update(T t);

    void delete(PK id);

    List<T> findAll();
}
