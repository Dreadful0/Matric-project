package com.demianenko.application.model.dao.implementations.mySql.daoImp.util.mappers;

import java.sql.ResultSet;

@FunctionalInterface
public interface EntityMapper<T> {
    T map(ResultSet rs);
}