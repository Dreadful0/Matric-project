package com.demianenko.application.model.dao.implementations.mySql.daoImp.util.mappers;

import com.demianenko.application.model.entities.University;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UniversityMapper {

    private final static Logger LOGGER = Logger.getLogger(UniversityMapper.class);

    public static University map(ResultSet rs) {
        University university = new University();
        try {
            university.setId(rs.getInt("id"));
            university.setName(rs.getString("name"));
        } catch (SQLException e){
            LOGGER.error("Can not map University", e);
            return null;
        }
        return university;
    }
}
