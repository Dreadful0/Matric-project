package com.demianenko.application.model.dao.implementations.mySql.daoImp;

import com.demianenko.application.model.dao.interfaces.daoInt.IGenericDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.List;

import static org.junit.Assert.*;

public abstract class AbstractMySqlDaoTest<T, PK extends Serializable> {
    protected IGenericDao<T, PK> dao;
    protected T entity;

    @Before
    public void setUp() throws Exception {
        entity = getTestObj();
        PK id = dao.add(entity);
        setId(entity, id);
    }

    @After
    public void tearDown() throws Exception {
        dao.delete(getId(entity));
    }

    @Test
    public void add() throws Exception {
        PK id = getId(entity);
        assertTrue(id != null);
        T newEntity = dao.find(id);
        assertEquals(entity, newEntity);
    }

    @Test
    public void find() throws Exception {
        PK id = getId(entity);
        T newEntity = dao.find(id);
        assertEquals(entity, newEntity);
        dao.delete(id);
        assertEquals(null, dao.find(id));
    }

    @Test
    public void update() throws Exception {
        PK id = getId(entity);
        T oldEntity = dao.find(id);
        updateObj(entity);
        dao.update(entity);
        T updatedEntity = dao.find(id);
        assertEquals(entity, updatedEntity);
        assertNotEquals(oldEntity, updatedEntity);
    }

    @Test
    public void delete() throws Exception {
        PK id = getId(entity);
        dao.delete(id);
        assertEquals(null, dao.find(id));
    }

    @Test
    public void findAll() throws Exception {
        List<T> oldList = dao.findAll();
        T ent = getTestObj();
        PK id = dao.add(ent);
        List<T> newList = dao.findAll();
        assertNotEquals(newList.size(), oldList.size());
        dao.delete(id);
    }

    protected abstract PK getId(T obj);

    protected abstract void setId(T obj, PK id);

    protected abstract T getTestObj();

    protected abstract void updateObj(T obj);
}

