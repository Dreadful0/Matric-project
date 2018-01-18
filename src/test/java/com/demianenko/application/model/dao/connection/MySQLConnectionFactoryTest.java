package com.demianenko.application.model.dao.connection;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLConnectionFactoryTest {

    private final static Logger LOGGER = Logger.getLogger(MySQLConnectionFactoryTest.class);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getConnection() {
        try {
            Connection conn = MySQLConnectionFactory.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            rs.next();
            LOGGER.debug(rs.getString(2));
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}