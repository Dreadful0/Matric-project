package com.demianenko.application.model.dao.interfaces.daoInt;

import com.demianenko.application.model.entities.Course;
import com.demianenko.application.model.entities.Speciality;

import java.util.List;

public interface ISpecialityDao extends IGenericDao<Speciality,Integer> {

    List<Speciality> getSpecialitiesByUniversityId(Integer id);

    List<Course> getRequiredCoursesList(Integer specialityId);

    void addCourse(Integer specialityId, Integer courseId);

}
