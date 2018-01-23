package com.demianenko.application.model.dao.implementations.mySql.daoImp.util.mappers;

import com.demianenko.application.model.entities.Role;
import com.demianenko.application.model.entities.User;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    private final static Logger LOGGER = Logger.getLogger(UserMapper.class);

    public static User map(ResultSet rs) {
        User user = new User();
        try {
            user.setEmail(rs.getString("email"));
            user.setFirstName(rs.getString("first_name"));
            user.setId(rs.getInt("id"));
            user.setPassword(rs.getString("password"));
            user.setSecondName(rs.getString("second_name"));
            user.setRole(Role.getRole(rs.getString("role")));
            user.setExamAttempts(rs.getInt("exam_attempts"));
        } catch (SQLException e){
            LOGGER.error("Can not map User", e);
            return null;
        }
        return user;
    }
}
