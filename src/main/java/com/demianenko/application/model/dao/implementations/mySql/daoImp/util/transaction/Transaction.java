package com.demianenko.application.model.dao.implementations.mySql.daoImp.util.transaction;

import com.demianenko.application.controller.exceptions.UserInfoException;
import com.demianenko.application.model.dao.connection.MySQLConnectionFactory;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface Transaction {

    Logger LOGGER = Logger.getLogger(Transaction.class);

    void pass();

    static void doTransaction(Transaction tx, int transactionIsolationLevel) throws UserInfoException {
        MySQLConnectionFactory cf = MySQLConnectionFactory.getInstance();
        Connection connection = cf.getConnection();
        try {
            boolean isAutoCommited = connection.getAutoCommit();
            connection.setAutoCommit(false);
            int oldIsolation = connection.getTransactionIsolation();
            if (oldIsolation != transactionIsolationLevel) {
                connection.setTransactionIsolation(transactionIsolationLevel);
            }
            tx.pass();
            if (isAutoCommited) {
                connection.commit();
                connection.setAutoCommit(isAutoCommited);
            }
            if (connection.getTransactionIsolation() != oldIsolation) {
                connection.setTransactionIsolation(oldIsolation);
            }

        } catch (SQLException e) {
            LOGGER.error("transaction failed", e);
            try {
                connection.rollback();
            } catch (SQLException e1) {
                LOGGER.error("can't rollback", e1);
                throw new UserInfoException("transactionRollbackFailed");
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error("can't close", e);
            }
        }
    }

    static void doTransaction(Transaction tx) throws UserInfoException {
        doTransaction(tx, Connection.TRANSACTION_READ_COMMITTED);
    }
}