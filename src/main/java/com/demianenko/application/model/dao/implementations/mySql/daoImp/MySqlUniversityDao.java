package com.demianenko.application.model.dao.implementations.mySql.daoImp;

import com.demianenko.application.model.dao.connection.IConnectionFactory;
import com.demianenko.application.model.dao.connection.MySQLConnectionFactory;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.MySqlGenericDao;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.mappers.UniversityMapper;
import com.demianenko.application.model.dao.interfaces.daoInt.IUniversityDao;
import com.demianenko.application.model.entities.University;

import java.util.List;

public class MySqlUniversityDao implements IUniversityDao {

    private IConnectionFactory cf;
    private MySqlGenericDao gDao;

    public MySqlUniversityDao() {
        this.cf = MySQLConnectionFactory.getInstance();
        gDao = new MySqlGenericDao(cf);
    }

    @Override
    public Integer add(University university) {
        return gDao.insert("INSERT INTO university " +
                        "(name)" +
                        "VALUES(?)",
                university.getName());
    }

    @Override
    public University find(Integer id) {
        University university;
        university = gDao.findObject("SELECT * FROM university " +
                "WHERE id=?", UniversityMapper::map, id);
        return university;
    }

    @Override
    public void update(University university) {
        gDao.update("UPDATE university " +
                        "SET " +
                        "name = ? " +
                        "WHERE id=?",
                university.getName(),
                university.getId());
    }

    @Override
    public void delete(Integer id) {
        gDao.remove("DELETE FROM university " +
                "WHERE id=?", id);
    }

    @Override
    public List<University> findAll() {
        List<University> list = gDao.findObjects("SELECT * FROM university "
                , UniversityMapper::map);
        return list;
    }
}
