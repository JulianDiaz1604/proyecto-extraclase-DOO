package edu.uco.artdly.data.dao.relational;

import java.sql.Connection;

import edu.uco.artdly.crosscutting.helper.PostgresqlConnectionHelper;

public class DAORelational {
    
    private Connection connection;

    protected DAORelational(final Connection connection){

        if(!PostgresqlConnectionHelper.connectionIsOpen(connection)){
            throw new RuntimeException();
        }

        this.connection = connection;

    }

    protected final Connection getConnection(){
        return connection;
    }

}
