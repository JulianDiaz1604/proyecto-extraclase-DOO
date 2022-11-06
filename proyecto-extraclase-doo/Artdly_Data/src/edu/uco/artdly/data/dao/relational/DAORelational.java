package edu.uco.artdly.data.dao.relational;

import java.sql.Connection;

import edu.uco.artdly.crosscutting.exception.data.CrosscuttingCustomException;

public class DAORelational {
    
    private Connection connection;

    protected DAORelational(final Connection connection){
        
        if (!SqlConnectionHelper.connectionIsOpen(connection)) {
            throw  CrosscuttingCustomException.//message
        }

        this.connection = connection;

    }

    protected final Connection getConnection() {
        return connection;
    }

}
