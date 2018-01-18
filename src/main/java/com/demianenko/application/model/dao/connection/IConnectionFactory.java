package com.demianenko.application.model.dao.connection;

import java.sql.Connection;

public interface IConnectionFactory {

	public Connection getConnection();
	
}
