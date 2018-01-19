package com.demianenko.application.model.dao.implementations.mySql.daoImp.util.mappers;

import com.demianenko.application.controller.util.converters.SqlToUtilDateConverter;
import com.demianenko.application.model.entities.ExamResult;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExamResultMapper {

    private final static Logger LOGGER = Logger.getLogger(ExamResultMapper.class);

    public static ExamResult map(ResultSet rs) {
        ExamResult examResult = new ExamResult();
        try {
            examResult.setId(rs.getInt("id"));
            examResult.setDate(SqlToUtilDateConverter.getUtil(rs.getDate("date")));
            examResult.setMark(rs.getInt("mark"));
            examResult.setCourseId(rs.getInt("course_id"));
            examResult.setUserId(rs.getInt("user_id"));
        } catch (SQLException e){
            LOGGER.error("Can not map ExamResult", e);
            return null;
        }
        return examResult;
    }
}
