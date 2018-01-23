package com.demianenko.application.model.dao.implementations.mySql.daoImp;

import com.demianenko.application.model.dao.connection.IConnectionFactory;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.MySqlGenericDao;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.mappers.SpecialityRequestMapper;
import com.demianenko.application.model.dao.interfaces.daoInt.ISpecialityRequestDao;
import com.demianenko.application.model.entities.SpecialityRequest;

import java.util.List;

public class MySqlSpecialityRequestDao implements ISpecialityRequestDao {

    private IConnectionFactory cf;
    private MySqlGenericDao gDao;

    public MySqlSpecialityRequestDao(IConnectionFactory connectionFactory) {
        this.cf = connectionFactory;
        gDao = new MySqlGenericDao(cf);
    }

    @Override
    public Integer add(SpecialityRequest specialityRequest) {
        return gDao.insert("INSERT INTO speciality_request " +
                        "(speciality_id," +
                        "user_id," +
                        "final_mark," +
                        "confirmed)"+
                        "VALUES(?,?,?,?)",
                specialityRequest.getSpecialityId(),
                specialityRequest.getUserId(),
                specialityRequest.getFinalMark(),
                specialityRequest.getConfirmed());
    }

    @Override
    public SpecialityRequest find(Integer id) {
        SpecialityRequest specialityRequest;
        specialityRequest = gDao.findObject("SELECT * FROM speciality_request " +
                "WHERE id=?", SpecialityRequestMapper::map, id);
        return specialityRequest;
    }

    @Override
    public void update(SpecialityRequest specialityRequest) {
        gDao.update("UPDATE speciality_request " +
                        "SET " +
                        "speciality_id = ?," +
                        "user_id = ?," +
                        "final_mark = ?," +
                        "confirmed = ? "+
                        "WHERE id=?",
                specialityRequest.getSpecialityId(),
                specialityRequest.getUserId(),
                specialityRequest.getFinalMark(),
                specialityRequest.getConfirmed(),
                specialityRequest.getId());
    }

    @Override
    public void delete(Integer id) {
        gDao.remove("DELETE FROM speciality_request " +
                "WHERE id=?", id);
    }

    @Override
    public List<SpecialityRequest> findAll() {
        List<SpecialityRequest> list = gDao.findObjects("SELECT * FROM speciality_request "
                , SpecialityRequestMapper::map);
        return list;
    }

    @Override
    public List<SpecialityRequest> getSpecialityRequestByUserId(Integer userId) {
        List<SpecialityRequest> list = gDao.findObjects("SELECT * FROM speciality_request "+
                "WHERE user_id=?" , SpecialityRequestMapper::map, userId);
        return list;
    }

    @Override
    public List<SpecialityRequest> getSpecialityRequestBySpecialityId(Integer specialityId) {
        List<SpecialityRequest> list = gDao.findObjects("SELECT * FROM speciality_request "+
                "WHERE speciality_id=? order by final_mark" , SpecialityRequestMapper::map, specialityId);
        return list;
    }
}
