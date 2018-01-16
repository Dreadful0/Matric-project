package com.demianenko.application.model.dao.connection;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MySQLConnectionFactory implements IConnectionFactory {

    private static final Logger LOGGER = LogManager.getLogger(MySQLConnectionFactory.class);

	private DataSource dataSource;
	private static MySQLConnectionFactory instance;
	
	private MySQLConnectionFactory(DataSource dataSource){
        LOGGER.debug("Constructing new MySQLConnectionFactory");
        this.dataSource = dataSource;
	}

	public static MySQLConnectionFactory getInstance(){
		if(instance == null) initFromProperties("db");
		return instance;
	}

	public Connection getConnection() throws SQLException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.warn("Can't create connection", e);
        }
        return null;
	}

    public static synchronized void initFromJNDI(String name) {
        LOGGER.debug("Initialising from JNDI");
	    if (instance != null) {
            return;
        }
        try {
            Context initialContext = new InitialContext();
            Context envContext = (Context) initialContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup(name);
            instance = new MySQLConnectionFactory(ds);
            LOGGER.debug("Initialising from JNDI completed");
        } catch (NamingException e) {
            LOGGER.error("Can't create initial context", e);
        }
    }

    public static synchronized void initFromProperties(String fileName) {
        LOGGER.debug("Initialising from properties file");
	    if (instance != null) {
            return;
        }
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle(fileName);
            MysqlDataSource mySqlDS = new MysqlDataSource();
            mySqlDS.setUser(resourceBundle.getString("user"));
            mySqlDS.setPassword(resourceBundle.getString("password"));
            mySqlDS.setServerName(resourceBundle.getString("server"));
            mySqlDS.setPortNumber(Integer.parseInt(resourceBundle.getString("port")));
            mySqlDS.setDatabaseName(resourceBundle.getString("db"));
            instance = new MySQLConnectionFactory(mySqlDS);
            LOGGER.debug("Initialising from properties file completed");
        } catch (Exception e) {
            LOGGER.error("Can not initialise from properties file");
        }
    }

}
