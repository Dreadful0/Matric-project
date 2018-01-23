package com.demianenko.application.controller.util.converters;

public class SqlToUtilDateConverter {

    public static java.sql.Date getSql (java.util.Date utilDate){
        if(utilDate == null) return null;
        return new java.sql.Date(utilDate.getTime());
    }

    public static java.util.Date getUtil (java.sql.Date sqlDate){
        if(sqlDate == null) return null;
        return new java.util.Date(sqlDate.getTime());
    }
}
