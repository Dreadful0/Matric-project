package com.demianenko.application.controller.services.implementations;

import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;
import com.demianenko.application.model.entities.Speciality;
import com.demianenko.application.model.entities.University;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Groups business logic for universities managing
 */
public class UniversityService {

    private final static Logger LOGGER = Logger.getLogger(UniversityService.class);

    private IDaoFactory daoFactory;

    public UniversityService(IDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Performs adding new University entity
     *
     * @param university University
     */
    public void addUniversity(University university){
        daoFactory.getUniversityDao().add(university);
    }

    /**
     * Performs deleting university by id
     *
     * @param id University id
     */
    public void deleteUniversity(int id){
        daoFactory.getUniversityDao().delete(id);
    }

    /**
     * Performs modifying university
     *
     * @param university University
     */
    public void modifyUniversity(University university){
        daoFactory.getUniversityDao().update(university);
    }

    /**
     * Returns all universities
     *
     * @return List of Universities
     */
    public List<University> getAllUniversities(){
        return daoFactory.getUniversityDao().findAll();
    }

    /**
     * Returns all universities with loaded specialities
     *
     * @return List of Universities
     */
    public List<University> getAllUniversitiesWithSpecialities(){
        List<University> universityList = daoFactory.getUniversityDao().findAll();
        List<Speciality> specialityList = ServiceFactory.getInstance()
                .getSpecialityService().getAllSpecialitiesWithCourses();

        universityList.forEach((x)->{
            x.setSpecialityList(specialityList.stream().filter((spec)->{
                return spec.getUniversityId()==x.getId();
            }).collect(Collectors.toList()));
        });
        return universityList;
    }
}
