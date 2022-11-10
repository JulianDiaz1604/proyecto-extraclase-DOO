package edu.uco.artdly.crosscutting.messages;

public class Messages {
    private Messages() {
        
    }
    public static class DAOFactory{
        
        private DAOFactory() {
            super();
        }

        public static final String TECHNICAL_MONGODB_NOT_IMPLEMENTED = "DAOFactory for MongoDB is not implemented yet";
        public static final String TECHNICAL_CASSANDRA_NOT_IMPLEMENTED = "DAOFactory for Cassandra is not implemented yet";
        public static final String TECHNICAL_MARIADB_NOT_IMPLEMENTED = "DAOFactory for MariaDB is not implemented yet";
        public static final String TECHNICAL_ORACLE_NOT_IMPLEMENTED = "DAOFactory for Oracle is not implemented yet";
        public static final String TECHNICAL_SQLSERVER_NOT_IMPLEMENTED = "DAOFactory for PostgreSQL is not implemented yet";
        public static final String TECHNICAL_MYSQL_NOT_IMPLEMENTED = "DAOFactory for MySQL is not implemented yet";
        public static final String TECHNICAL_UNEXPECTED_DAOFACTORY = "Unexpected DAOFactory";
    }
    public static class DAORelational{

        private DAORelational() {
            super();
        }
        public static final String TECHNICAL_CONNECTION_IS_CLOSED = "Connection already is closed";

        
    }
    public static class PostgresqlConnectionHelper {

        private PostgresqlConnectionHelper(){
            super();
        }
        public static final String TECHNICAL_CONNECTION_ALREADY_IS_OPEN = "Connection already is open";
        public static final String TECHNICAL_CONNECTION_IS_NULL = "Connection is null";
        public static final String TECHNICAL_CONNECTION_IS_CLOSED = "Connection is closed";//
        public static final String TECHNICAL_CONNECTION_ALREADY_IS_CLOSED = "Connection already is closed";//
        public static final String TECHNICAL_PROBLEM_CLOSING_CONNECTION = "There was a problem trying to close connection";//
        public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_INIT_TRANSACTION = "Connection is closed to init the current transaction";//
        public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_ROLLBACK_TRANSACTION = "Connection is closed to rollback the current transaction";    //  
        public static final String TECHNICAL_CONNECTION_IS_CLOSED_FOR_COMMIT_TRANSACTION = "Connection is closed to commit the current transaction in PostgresqlConnectionHelp";   //   
        public static final String TECHNICAL_PROBLEM_TRY_INIT_TRANSACTION = "There was a problem trying to init transaction";//
        public static final String TECHNICAL_PROBLEM_TRY_COMMIT_TRANSACTION = "There was a problem trying to close connection";//
        public static final String TECHNICAL_PROBLEM_TRY_ROLLBACK_TRANSACTION = "There was a problem trying to rollback trasaction";//
    }
    public static class UUIDHelper {
        private UUIDHelper(){
            super();
        }   
        public static final String TECHNICAL_UUID_FROM_STRING_INVALID = "The UUID to convert doesn't have a correct format";
        public static final String TECHNICAL_UUID_FROM_STRING_UNEXPECTED_ERROR = "There was an unexpected error";
        }
    public static class PostgreSQLDAOFactory{
        private PostgreSQLDAOFactory() {
            super();
        }
        public static final String TECHNICAL_PROBLEM_CLOSE_CONNECTION = "There was a problem trying to close connection with the current connection in SQLServerDAOFactory";
        public static final String TECHNICAL_PROBLEM_CONFIRM_TRANSACTION = "There was a problem trying to confirm trasaction with the current connection in SQLServerDAOFactory";
        public static final String TECHNICAL_PROBLEM_CONNECT_DATABASE = "There was a problem trying to connect to Data base ";
        public static final String TECHNICAL_PROBLEM_ROLLBACK_TRANSACTION = "There was a problem trying to rollback transaction";
        public static final String TECHNICAL_PROBLEM_INIT_TRANSACTION = "There was a problem trying to init trasaction with the current connection in SQLServerDAOFactory";
    }
    public static class ArtworkPostgresqlDAO{
        private ArtworkPostgresqlDAO() {
            super();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_ARTWORK = "There was a problem trying to create the artwork";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_ARTWORK = "There was a unexpected problem trying to create the artwork";
        public static final String TECHNICAL_PROBLEM_UPDATE_ARTWORK = "There was a problem trying to update the artwork";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_ARTWORK = "There was a unexpected problem trying to update the artwork";
        public static final String TECHNICAL_PROBLEM_DELETE_ARTWORK = "There was a problem trying to delete the artwork";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_ARTWORK = "There was a unexpected problem trying to delete the artwork";
        public static final String TECHNICAL_PROBLEM_FILLRESULTS_ARTWORK = "There was a problem trying to fillResults the artwork";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILLRESULTS_ARTWORK = "There was a unexpected problem trying to fillResults the artwork";
        public static final String TECHNICAL_PROBLEM_FILLARTWORKDTO_ARTWORK = "There was a problem trying to fillArtworkDTO the artwork";
        public static final String TECHNICAL_PROBLEM_FILLFILEDTO_ARTWORK = "There was a problem trying to fillFileDTO the artwork";
        public static final String TECHNICAL_PROBLEM_FILLFILETYPEDTO_ARTWORK = "There was a problem trying to fillFileTypeDTO the artwork";
        public static final String TECHNICAL_PROBLEM_FILLARTWORKTYPEDTO_ARTWORK = "There was a problem trying to fillArtworkTypeDTO the artwork";
        
        public static final String TECHNICAL_PROBLEM_FILLUSERDTO_ARTWORK = "There was a problem trying to fillUserDTO the artwork";
        public static final String TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_ARTWORK = "There was a problem trying to prepareAndExecuteQuery the artwork";
        public static final String TECHNICAL_PROBLEM_SETPARAMETERSVALUES_ARTWORK = "There was a problem trying to setParametersValues the artwork";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_SETPARAMETERSVALUES_ARTWORK = "There was a unexpected problem trying to setParametersValues the artwork";
        public static final String TECHNICAL_PROBLEM_EXECUTEQUERY_ARTWORK = "There was a problem trying to executeQuery the artwork";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTEQUERY_ARTWORK = "There was a unexpected problem trying to executeQuery the artwork";
    }

    public static class ArtworkTypePostgresqlDAO{
        private ArtworkTypePostgresqlDAO() {
            super();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_ARTWORKTYPE = "There was a problem trying to create the artworktype";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_ARTWORKTYPE = "There was a unexpected problem trying to create the artworktype";
        public static final String TECHNICAL_PROBLEM_UPDATE_ARTWORKTYPE = "There was a problem trying to update the artworktype";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_ARTWORKTYPE = "There was a unexpected problem trying to update the artworktype";
        public static final String TECHNICAL_PROBLEM_DELETE_ARTWORKTYPE = "There was a problem trying to delete the artworktype";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_ARTWORKTYPE = "There was a unexpected problem trying to delete the artworktype";
        public static final String TECHNICAL_PROBLEM_FILLRESULTS_ARTWORKTYPE = "There was a problem trying to fillResults the artworktype";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILLRESULTS_ARTWORKTYPE = "There was a unexpected problem trying to fillResults the artworktype";
        public static final String TECHNICAL_PROBLEM_FILLARTWORKTYPEDTO_ARTWORKTYPE = "There was a problem trying to fillArtworkTypeDTO the artworktype";
       
        public static final String TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_ARTWORKTYPE = "There was a problem trying to prepareAndExecuteQuery the artworktype";
        
        public static final String TECHNICAL_PROBLEM_SETPARAMETERSVALUES_ARTWORKTYPE = "There was a problem trying to setParametersValues the artworktype";
        
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_SETPARAMETERSVALUES_ARTWORKTYPE = "There was a unexpected problem trying to setParametersValues the artworktype";
        
        public static final String TECHNICAL_PROBLEM_EXECUTEQUERY_ARTWORKTYPE = "There was a problem trying to executeQuery the artworktype";
        
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTEQUERY_ARTWORKTYPE = "There was a unexpected problem trying to executeQuery the artworktype";
    }
    public static class CategoryArtworkPostgresqlDAO{
        private CategoryArtworkPostgresqlDAO (){
            super();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_CATEGORYARTWORK = "There was a problem trying to create the categoryArtwork";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_CATEGORYARTWORK = "There was a unexpected problem trying to create the categoryArtwork";
        public static final String TECHNICAL_PROBLEM_UPDATE_CATEGORYARTWORK = "There was a problem trying to update the categoryArtwork";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_CATEGORYARTWORK = "There was a unexpected problem trying to update the categoryArtwork";
        public static final String TECHNICAL_PROBLEM_DELETE_CATEGORYARTWORK = "There was a problem trying to delete the categoryArtwork";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_CATEGORYARTWORK = "There was a unexpected problem trying to delete the categoryArtwork";
        public static final String TECHNICAL_PROBLEM_FILLRESULTS_CATEGORYARTWORK = "There was a problem trying to fillResults the categoryArtwork";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILLRESULTS_CATEGORYARTWORK = "There was a unexpected problem trying to fillResults the categoryArtwork";
        
        public static final String TECHNICAL_PROBLEM_FILLCATEGORYARTWORKDTO_CATEGORYARTWORK = "There was a problem trying to fillCategoryArtworkTypeDTO the categoryArtwork";
        public static final String TECHNICAL_PROBLEM_FILLARTWORKDTO_CATEGORYARTWORK = "There was a problem trying to fillArtworkDTO the categoryArtwork";
        public static final String TECHNICAL_PROBLEM_FILLFILEDTO_CATEGORYARTWORK ="There was a problem trying to fillFileDTO the categoryArtwork";
        public static final String TECHNICAL_PROBLEM_FILLFILETYPEDTO_CATEGORYARTWORK ="There was a problem trying to fillFileTypeDTO the categoryArtwork";
        public static final String TECHNICAL_PROBLEM_FILLARTWORKTYPEDTO_CATEGORYARTWORK = "There was a problem trying to fillArtworkTypeDTO the categoryArtwork";
        public static final String TECHNICAL_PROBLEM_FILLUSERDTO_CATEGORYARTWORK = "There was a problem trying to fillUserDTO the categoryArtwork ";
        public static final String TECHNICAL_PROBLEM_FILLCATEGORYDTO_CATEGORYARTWORK = "There was a problem trying to fillCategoryDTO the categoryArtwork ";
        public static final String TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_CATEGORYARTWORK = "There was a problem trying to prepareAndExecuteQuery the categoryArtwork";
        
        public static final String TECHNICAL_PROBLEM_SETPARAMETERSVALUES_CATEGORYARTWORK = "There was a problem trying to setParametersValues the categoryArtwork";
        
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_SETPARAMETERSVALUES_CATEGORYARTWORK = "There was a unexpected problem trying to setParametersValues the categoryArtwork";
        
        public static final String TECHNICAL_PROBLEM_EXECUTEQUERY_CATEGORYARTWORK = "There was a problem trying to executeQuery the categoryArtwork";
        
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTEQUERY_CATEGORYARTWORK = "There was a unexpected problem trying to executeQuery the categoryArtwork";
    }
    
}
