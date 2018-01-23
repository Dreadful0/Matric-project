package com.demianenko.application.controller.services.implementations;

import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;
import com.demianenko.application.model.entities.Speciality;
import com.demianenko.application.model.entities.University;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class UniversityService {

    private final static Logger LOGGER = Logger.getLogger(UniversityService.class);

    private IDaoFactory daoFactory;

    public UniversityService(IDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void addUniversity(University university){
        daoFactory.getUniversityDao().add(university);
    }

    public void deleteUniversity(int id){
        daoFactory.getUniversityDao().delete(id);
    }

    public  void modifyUniversity(University university){
        daoFactory.getUniversityDao().update(university);
    }

    public List<University> getAllUniversities(){
        return daoFactory.getUniversityDao().findAll();
    }

    public List<University> getAllUniversitiesWithSpecialities(){
        List<University> universityList = daoFactory.getUniversityDao().findAll();
        List<Speciality> specialityList = ServiceFactory.getInstance()
                .getSpecialityService().getAllSpecialitiesWithCourses();

        universityList.stream().forEach((x)->{
            x.setSpecialityList(specialityList.stream().filter((spec)->{
                return spec.getUniversityId()==x.getId();
            }).collect(Collectors.toList()));
            //x.setSpecialityList(daoFactory.getSpecialityDao()
            //        .getSpecialitiesByUniversityId(x.getId()));
        });
        return universityList;
    }
}
