package com.demianenko.application.model.dao.interfaces.daoInt;

import com.demianenko.application.model.entities.Speciality;

import java.util.List;

public interface ISpecialityDao extends IGenericDao<Speciality,Integer> {

    public List<Speciality> getSpecialitiesByUniversityId(Integer id);

}
