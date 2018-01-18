package com.demianenko.application.model.dao.implementations.mySql.daoImp.util.mappers;

import com.demianenko.application.model.entities.Speciality;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialityMapper {

    private final static Logger LOGGER = Logger.getLogger(SpecialityMapper.class);

    public static Speciality map(ResultSet rs) {
        Speciality speciality = new Speciality();
        try {
            speciality.setId(rs.getInt("id"));
            speciality.setName(rs.getString("name"));
            speciality.setUniversityId(rs.getInt("university_id"));
            speciality.setStudentsNumber(rs.getInt("students_number"));
        } catch (SQLException e){
            LOGGER.error("Can not map Speciality", e);
            return null;
        }
        return speciality;
    }
}
