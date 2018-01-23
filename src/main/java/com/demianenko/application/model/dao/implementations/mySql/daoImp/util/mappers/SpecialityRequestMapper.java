package com.demianenko.application.model.dao.implementations.mySql.daoImp.util.mappers;

import com.demianenko.application.model.entities.SpecialityRequest;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SpecialityRequestMapper {

    private final static Logger LOGGER = Logger.getLogger(SpecialityRequestMapper.class);

    public static SpecialityRequest map(ResultSet rs) {
        SpecialityRequest specialityRequest = new SpecialityRequest();
        try {
            specialityRequest.setId(rs.getInt("id"));
            specialityRequest.setSpecialityId(rs.getInt("speciality_id"));
            specialityRequest.setUserId(rs.getInt("user_id"));
            specialityRequest.setFinalMark(rs.getInt("final_mark"));
            specialityRequest.setConfirmed(rs.getString("confirmed"));
        } catch (SQLException e){
            LOGGER.error("Can not map SpecialityRequest", e);
            return null;
        }
        return specialityRequest;
    }
}
