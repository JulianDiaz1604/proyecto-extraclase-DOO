package edu.uco.artdly.data.dao.relational;

import java.sql.Connection;

import edu.uco.artdly.crosscutting.exception.data.DataCustomException;
import edu.uco.artdly.crosscutting.helper.SqlConnectionHelper;
import edu.uco.artdly.crosscutting.messages.Messages;

public class DAORelational {
    
    private Connection connection;

    protected DAORelational (final Connection connection){

        if(!SqlConnectionHelper.connectionIsOpen(connection)){
            throw DataCustomException.CreateTechnicalException(Messages.DAORelational.TECHNICAL_CONNECTION_IS_CLOSED);    
        }

        this.connection = connection;

    }

    protected final Connection getConnection(){
        return connection;
    }

}
