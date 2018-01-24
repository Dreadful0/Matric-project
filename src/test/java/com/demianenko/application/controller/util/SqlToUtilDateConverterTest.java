package com.demianenko.application.controller.util;

import com.demianenko.application.controller.util.converters.SqlToUtilDateConverter;
import org.junit.After;
import org.junit.Assert;
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
        Assert.assertEquals(SqlToUtilDateConverter.getSql(utilDate),sqlDate);
    }

    @Test
    public void getUtil() {
        Assert.assertEquals(SqlToUtilDateConverter.getUtil(sqlDate),utilDate);
    }
}