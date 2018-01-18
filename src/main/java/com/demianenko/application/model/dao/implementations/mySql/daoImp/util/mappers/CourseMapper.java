package com.demianenko.application.model.dao.implementations.mySql.daoImp.util.mappers;

import com.demianenko.application.model.entities.Course;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseMapper {

    private final static Logger LOGGER = Logger.getLogger(CourseMapper.class);

    public static Course map(ResultSet rs) {
        Course course = new Course();
        try {
            course.setId(rs.getInt("id"));
            course.setName(rs.getString("name"));
        } catch (SQLException e){
            LOGGER.error("Can not map Course", e);
            return null;
        }
        return course;
    }
}
