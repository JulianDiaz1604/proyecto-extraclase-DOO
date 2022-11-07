package edu.uco.artdly.data.dao.relational;

import java.sql.Connection;

import edu.uco.artdly.crosscutting.helper.PostgreSQLConnectionHelper;

public class DAORelational {
    
    private Connection connection;

    protected DAORelational(final Connection connection){

        if(!PostgreSQLConnectionHelper.connectionIsOpen(connection)){
            throw new RuntimeException();
        }

        this.connection = connection;

    }

    protected final Connection getConnection(){
        return connection;
    }

}
