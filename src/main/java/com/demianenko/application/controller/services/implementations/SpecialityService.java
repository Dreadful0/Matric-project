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

    public void addSpeciality(Speciality speciality, List<Integer> coursesId){
        Integer specialityId = daoFactory.getSpecialityDao().add(speciality);
        for (Integer id: coursesId) {
            daoFactory.getSpecialityDao().addCourse(specialityId, id);
        }
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

    public List<Speciality> getAllSpecialitiesWithCourses(){
        List<Speciality> specialityList = daoFactory.getSpecialityDao().findAll();
        specialityList.stream().forEach((x)->{
            x.setRequiredCourses(daoFactory.getSpecialityDao().getRequiredCoursesList(x.getId()));
        });
        return specialityList;
    }
}
