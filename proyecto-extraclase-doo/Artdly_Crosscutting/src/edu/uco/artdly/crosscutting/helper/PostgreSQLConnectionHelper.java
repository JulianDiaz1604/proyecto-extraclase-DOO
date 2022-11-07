package edu.uco.artdly.crosscutting.helper;

import java.sql.Connection;
import java.sql.SQLException;

import edu.uco.artdly.crosscutting.exception.data.CrosscuttingCustomException;

public class PostgreSQLConnectionHelper {
    
    private PostgreSQLConnectionHelper(){
        super();
    } 

    public static final boolean connectionIsNull(final Connection connection){
        return ObjectHelper.isNull(connection);
    }

    public static final boolean connectionIsOpen(final Connection connection) {
        try {
            return !ObjectHelper.isNull(connection) && !connection.isClosed();
        } catch (final SQLException exception) {
            throw CrosscuttingCustomException.CreateTechnicalException(/**Messages.SqlConnectionHelper.TECHNICAL_CONNECTION_IS_CLOSED,exception**/"");
                    
        }
    }

    public static final void closeConnection(final Connection connection){
        try {
            if(!connectionIsOpen(connection)){
                throw CrosscuttingCustomException.CreateTechnicalException(/**Messages.SqlConnectionHelper.TECHNICAL_CONNECTION_ALREADY_IS_CLOSED**/"TO-DO");
            }
            connection.close();
        } catch (final CrosscuttingCustomException exception) {
            throw exception;
        }catch(final SQLException exception) {
            throw CrosscuttingCustomException.CreateTechnicalException(/**Messages.SqlConnectionHelper.TECHNICAL_PROBLEM_CLOSING_CONNECTION, exception**/"TO-DO");
            
        }
    }

    public static final void initTrasaction(final Connection connection){
        try {
            if(!connectionIsOpen(connection)){
                throw CrosscuttingCustomException.CreateTechnicalException(/**Messages.SqlConnectionHelper.TECHNICAL_CONNECTION_IS_CLOSED_FOR_INIT_TRANSACTION**/"TO-DO");
            }
            connection.setAutoCommit(false);
        } catch (CrosscuttingCustomException exception) {
           throw exception;
        }catch (SQLException exception) {
            throw CrosscuttingCustomException.CreateTechnicalException(/**Messages.SqlConnectionHelper.TECHNICAL_PROBLEM_TRY_INIT_TRANSACTION, exception**/"TO-DO");
        }
        
    }
    public static final void commitTrasaction(final Connection connection){
        try {
            if(!connectionIsOpen(connection)){
                throw CrosscuttingCustomException.CreateTechnicalException(/**Messages.SqlConnectionHelper.TECHNICAL_CONNECTION_IS_CLOSED_FOR_COMMIT_TRANSACTION**/"TO-DO");
            }
            connection.setAutoCommit(false);
        } catch (CrosscuttingCustomException exception) {
           throw exception;
        }catch (SQLException exception) {
            throw CrosscuttingCustomException.CreateTechnicalException(/**Messages.SqlConnectionHelper.TECHNICAL_PROBLEM_TRY_COMMIT_TRANSACTION, exception**/"TO-DO");
        }
        
    }
    public static final void rollbackTrasaction(final Connection connection){
        try {
            if(!connectionIsOpen(connection)){
                throw CrosscuttingCustomException.CreateTechnicalException(/**Messages.SqlConnectionHelper.TECHNICAL_CONNECTION_IS_CLOSED_FOR_ROLLBACK_TRANSACTION**/"TO-DO");
            }
            connection.setAutoCommit(false);
        } catch (CrosscuttingCustomException exception) {
           throw exception;
        }catch (SQLException exception) {
            throw CrosscuttingCustomException.CreateTechnicalException(/**Messages.SqlConnectionHelper.TECHNICAL_PROBLEM_TRY_ROLLBACK_TRANSACTION, exception**/"TO-DO");
        }
        
    }

}
