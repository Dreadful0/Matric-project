package com.demianenko.application.controller.services.implementations;

import com.demianenko.application.controller.exceptions.UserInfoException;
import com.demianenko.application.model.dao.implementations.mySql.daoImp.util.transaction.Transaction;
import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;
import com.demianenko.application.model.entities.Speciality;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Groups business logic for managing specialities
 */
public class SpecialityService {

    private final static Logger LOGGER = Logger.getLogger(SpecialityService.class);

    private IDaoFactory daoFactory;

    public SpecialityService(IDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    /**
     * Performs adding new speciality with required courses
     *
     * @param speciality new Speciality entity
     * @param coursesId list of course ids
     * @throws UserInfoException if transaction rollback fails
     */
    public void addSpeciality(Speciality speciality, List<Integer> coursesId) throws UserInfoException {
        Transaction.doTransaction(()->{
            Integer specialityId = daoFactory.getSpecialityDao().add(speciality);
            for (Integer id: coursesId) {
                daoFactory.getSpecialityDao().addCourse(specialityId, id);
            }
        });
    }

    /**
     * Performs deleting Speciality by id
     *
     * @param specialityId Speciality id
     */
    public void deleteSpeciality(int specialityId){
        daoFactory.getSpecialityDao().delete(specialityId);
    }

    /**
     * Performs modifying Speciality
     *
     * @param speciality modified Speciality
     */
    public void modifySpeciality(Speciality speciality){
        daoFactory.getSpecialityDao().update(speciality);
    }

    /**
     * Returns all specialities
     *
     * @return List of Specialities
     */
    public List<Speciality> getAllSpecialities(){
        return daoFactory.getSpecialityDao().findAll();
    }

    /**
     * Returns all specialities with loaded required courses
     *
     * @return List of Specialities
     */
    public List<Speciality> getAllSpecialitiesWithCourses(){
        List<Speciality> specialityList = daoFactory.getSpecialityDao().findAll();
        specialityList.stream().forEach((x)->{
            x.setRequiredCourses(daoFactory.getSpecialityDao().getRequiredCoursesList(x.getId()));
        });
        return specialityList;
    }
}
