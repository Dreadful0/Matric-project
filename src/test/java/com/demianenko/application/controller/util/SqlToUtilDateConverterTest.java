package com.demianenko.application.controller.util;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SqlToUtilDateConverterTest {

    java.util.Date utilDate = new java.util.Date();
    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getSql() {
        System.out.println(SqlToUtilDateConverter.getSql(utilDate));
        System.out.println(sqlDate);
    }

    @Test
    public void getUtil() {
        System.out.println(SqlToUtilDateConverter.getUtil(sqlDate));
        System.out.println(utilDate);
    }
}