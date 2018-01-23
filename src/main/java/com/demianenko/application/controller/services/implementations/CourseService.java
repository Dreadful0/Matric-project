package com.demianenko.application.controller.services.implementations;

import com.demianenko.application.model.dao.interfaces.factoryInt.IDaoFactory;
import com.demianenko.application.model.entities.Course;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Groups business logic for courses administration
 */
public class CourseService {

    private final static Logger LOGGER = Logger.getLogger(CourseService.class);

    private IDaoFactory daoFactory;

    public CourseService(IDaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void addCourse(Course course){
        daoFactory.getCourseDao().add(course);
    }

    public void deleteCourse(int id){
        daoFactory.getCourseDao().delete(id);
    }

    public  void modifyCourse(Course course){
        daoFactory.getCourseDao().update(course);
    }

    public List<Course> getAllCourses(){
        return daoFactory.getCourseDao().findAll();
    }
}
