package com.demianenko.application.controller.services.implementations;

import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;
import com.demianenko.application.model.entities.Speciality;
import org.apache.log4j.Logger;

import java.util.List;

public class SpecialityService {

    private final static Logger LOGGER = Logger.getLogger(SpecialityService.class);

    private IDaoFactory daoFactory;

    public SpecialityService(IDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void addSpeciality(Speciality speciality){
        daoFactory.getSpecialityDao().add(speciality);
    }

    public void deleteSpeciality(int id){
        daoFactory.getSpecialityDao().delete(id);
    }

    public  void modifySpeciality(Speciality speciality){
        daoFactory.getSpecialityDao().update(speciality);
    }

    public List<Speciality> getAllSpecialities(){
        return daoFactory.getSpecialityDao().findAll();
    }
}
