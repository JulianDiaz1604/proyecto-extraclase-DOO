package edu.uco.artdly.crosscutting.helper;

import java.sql.Connection;
import java.sql.SQLException;

import edu.uco.artdly.crosscutting.exception.crosscutting.CrosscuttingCustomException;
import edu.uco.artdly.crosscutting.messages.Messages;

public class SqlConnectionHelper {
    
    private SqlConnectionHelper(){
        super();
    } 

    public static final boolean connectionIsNull(final Connection connection){
        return ObjectHelper.isNull(connection);
    }

    public static final boolean connectionIsOpen(final Connection connection) {
        try {
            return !ObjectHelper.isNull(connection) && !connection.isClosed();
        } catch (final SQLException exception) {
            throw CrosscuttingCustomException.CreateTechnicalException(Messages.PostgresqlConnectionHelper.TECHNICAL_CONNECTION_IS_CLOSED, exception);
                    
        }
    }

    public static final void closeConnection(final Connection connection){
        try {
            if(!connectionIsOpen(connection)){
                throw CrosscuttingCustomException.CreateTechnicalException(Messages.PostgresqlConnectionHelper.TECHNICAL_CONNECTION_ALREADY_IS_CLOSED);
            }
            connection.close();
        } catch (final CrosscuttingCustomException exception) {
            throw exception;
        }catch(final SQLException exception) {
            throw CrosscuttingCustomException.CreateTechnicalException(Messages.PostgresqlConnectionHelper.TECHNICAL_PROBLEM_CLOSING_CONNECTION, exception);
            
        }
    }

    public static final void initTrasaction(final Connection connection){
        try {
            if(!connectionIsOpen(connection)){
                throw CrosscuttingCustomException.CreateTechnicalException(Messages.PostgresqlConnectionHelper.TECHNICAL_CONNECTION_IS_CLOSED_FOR_INIT_TRANSACTION);
            }
            connection.setAutoCommit(false);
        } catch (CrosscuttingCustomException exception) {
           throw exception;
        }catch (SQLException exception) {
            throw CrosscuttingCustomException.CreateTechnicalException(Messages.PostgresqlConnectionHelper.TECHNICAL_PROBLEM_TRY_INIT_TRANSACTION, exception);
        }
        
    }
    public static final void commitTrasaction(final Connection connection){
        try {
            if(!connectionIsOpen(connection)){
                throw CrosscuttingCustomException.CreateTechnicalException(Messages.PostgresqlConnectionHelper.TECHNICAL_CONNECTION_IS_CLOSED_FOR_COMMIT_TRANSACTION);
            }
            connection.commit();
            connection.setAutoCommit(false);
        } catch (CrosscuttingCustomException exception) {
           throw exception;
        }catch (SQLException exception) {
            throw CrosscuttingCustomException.CreateTechnicalException(Messages.PostgresqlConnectionHelper.TECHNICAL_PROBLEM_TRY_COMMIT_TRANSACTION, exception);
        }
        
    }
    public static final void rollbackTrasaction(final Connection connection){
        try {
            if(!connectionIsOpen(connection)){
                throw CrosscuttingCustomException.CreateTechnicalException(Messages.PostgresqlConnectionHelper.TECHNICAL_CONNECTION_IS_CLOSED_FOR_ROLLBACK_TRANSACTION);
            }
            connection.setAutoCommit(false);
        } catch (CrosscuttingCustomException exception) {
           throw exception;
        }catch (SQLException exception) {
            throw CrosscuttingCustomException.CreateTechnicalException(Messages.PostgresqlConnectionHelper.TECHNICAL_PROBLEM_TRY_ROLLBACK_TRANSACTION, exception);
        }
        
    }

}
