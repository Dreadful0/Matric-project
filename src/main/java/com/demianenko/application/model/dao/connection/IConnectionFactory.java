package com.demianenko.application.model.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnectionFactory {

	public Connection getConnection() throws SQLException;
	
}
