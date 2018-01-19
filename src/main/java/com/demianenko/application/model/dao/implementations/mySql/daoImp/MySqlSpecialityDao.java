package com.demianenko.application.model.dao.implementations.mySql.daoImp;

import com.demianenko.application.model.dao.connection.IConnectionFactory;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.MySqlGenericDao;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.mappers.SpecialityMapper;
import com.demianenko.application.model.dao.interfaces.daoInt.ISpecialityDao;
import com.demianenko.application.model.entities.Speciality;

import java.util.List;

public class MySqlSpecialityDao implements ISpecialityDao {

    private IConnectionFactory cf;
    private MySqlGenericDao gDao;

    public MySqlSpecialityDao(IConnectionFactory connectionFactory) {
        this.cf = connectionFactory;
        gDao = new MySqlGenericDao(cf);
    }

    @Override
    public Integer add(Speciality speciality) {
        return gDao.insert("INSERT INTO speciality " +
                        "(name," +
                        "students_number," +
                        "university_id)" +
                        "VALUES(?,?,?)",
                speciality.getName(),
                speciality.getStudentsNumber(),
                speciality.getUniversityId());
    }

    @Override
    public Speciality find(Integer id) {
        Speciality speciality;
        speciality = gDao.findObject("SELECT * FROM speciality " +
                "WHERE id=?", SpecialityMapper::map, id);
        return speciality;
    }

    @Override
    public void update(Speciality speciality) {
        gDao.update("UPDATE speciality " +
                        "SET " +
                        "name = ?," +
                        "students_number = ?," +
                        "university_id = ? " +
                        "WHERE id=?",
                speciality.getName(),
                speciality.getStudentsNumber(),
                speciality.getUniversityId(),
                speciality.getId());
    }

    @Override
    public void delete(Integer id) {
        gDao.remove("DELETE FROM speciality " +
                "WHERE id=?", id);
    }

    @Override
    public List<Speciality> findAll() {
        List<Speciality> list = gDao.findObjects("SELECT * FROM speciality "
                , SpecialityMapper::map);
        return list;
    }
}
