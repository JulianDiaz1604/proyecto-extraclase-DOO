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
    public static class CategoryPostgresqlDAO{
        private CategoryPostgresqlDAO () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_CATEGORY = "There was a problem trying to create the category";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_CATEGORY = "There was a unexpected problem trying to create the category";
        public static final String TECHNICAL_PROBLEM_UPDATE_CATEGORY = "There was a problem trying to update the category";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_CATEGORY = "There was a unexpected problem trying to update the category";
        
        public static final String TECHNICAL_PROBLEM_EXECUTEQUERY_CATEGORY = "There was a problem trying to executeQuery the category";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTEQUERY_CATEGORY = "There was a unexpected problem trying to executeQuery the category";
        
        public static final String TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_CATEGORY = "There was a problem trying to prepareAndExecuteQuery the category";
        
        public static final String TECHNICAL_PROBLEM_SETPARAMETERSVALUES_CATEGORY = "There was a problem trying to setParametersValues the category";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_SETPARAMETERSVALUES_CATEGORY = "There was a unexpected problem trying to setParametersValues the category";
        
        public static final String TECHNICAL_PROBLEM_FILLRESULTS_CATEGORY = "There was a problem trying to fillResults the category";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILLRESULTS_CATEGORY = "There was a unexpected problem trying to fillResults the category";
        public static final String TECHNICAL_PROBLEM_FILLCATEGORYDTO_CATEGORY = "There was a problem trying to fillCategoryDTO the category ";
    }
    public static class CommentPostgresqlDAO{
        private CommentPostgresqlDAO () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_COMMENT = "There was a problem trying to create the comment";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_COMMENT = "There was a unexpected problem trying to create the comment";
        public static final String TECHNICAL_PROBLEM_DELETE_COMMENT = "There was a problem trying to delete the comment";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_COMMENT= "There was a unexpected problem trying to delete the comment";
        
        public static final String TECHNICAL_PROBLEM_FILLRESULTS_COMMENT = "There was a problem trying to fillResults the comment";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILLRESULTS_COMMENT = "There was a unexpected problem trying to fillResults the comment";
        
        public static final String TECHNICAL_PROBLEM_FILLCOMMENTDTO_COMMENT= "There was a problem trying to fillCategoryDTO the comment ";
        
        public static final String TECHNICAL_PROBLEM_FILLCOMMENTUSERDTO_COMMENT = "There was a problem trying to fillUserDTO the comment ";
        
        public static final String TECHNICAL_PROBLEM_FILLARTWORKDTO_COMMENT = "There was a problem trying to fillArtworkDTO the comment";
        
        public static final String TECHNICAL_PROBLEM_FILLARTWORKTYPEDTO_COMMENT = "There was a problem trying to fillArtworkTypeDTO the comment";
        
        public static final String TECHNICAL_PROBLEM_FILLFILEDTO_COMMENT ="There was a problem trying to fillFileDTO the comment";
        public static final String TECHNICAL_PROBLEM_FILLFILETYPEDTO_COMMENT="There was a problem trying to fillFileTypeDTO the comment"; 
        
        public static final String TECHNICAL_PROBLEM_FILLUSERDTO_COMMENT = "There was a problem trying to fillUserDTO the comment";
        
        
        public static final String TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_COMMENT = "There was a problem trying to prepareAndExecuteQuery the comment";
        
        public static final String TECHNICAL_PROBLEM_SETPARAMETERSVALUES_COMMENT = "There was a problem trying to setParametersValues the comment";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_SETPARAMETERSVALUES_COMMENT = "There was a unexpected problem trying to setParametersValues the comment";
        
        public static final String TECHNICAL_PROBLEM_EXECUTEQUERY_COMMENT = "There was a problem trying to executeQuery the comment";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTEQUERY_COMMENT = "There was a unexpected problem trying to executeQuery the comment";
    }
    public static class FilePostgresqlDAO{
        private FilePostgresqlDAO() {
            super ();        
        }
        public static final String TECHNICAL_PROBLEM_CREATE_FILE = "There was a problem trying to create the file";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_FILE = "There was a unexpected problem trying to create the file";
        public static final String TECHNICAL_PROBLEM_UPDATE_FILE = "There was a problem trying to update the file";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_FILE= "There was a unexpected problem trying to update the file";
        public static final String TECHNICAL_PROBLEM_DELETE_FILE = "There was a problem trying to delete the file";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_FILE = "There was a unexpected problem trying to delete the file";
        
        public static final String TECHNICAL_PROBLEM_FILLRESULTS_FILE = "There was a problem trying to fillResults the file";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILLRESULTS_FILE = "There was a unexpected problem trying to fillResults the file";
        
        public static final String TECHNICAL_PROBLEM_FILLFILEDTO_FILE ="There was a problem trying to fillFileDTO the file";
        public static final String TECHNICAL_PROBLEM_FILLFILETYPEDTO_FILE ="There was a problem trying to fillFileTypeDTO the file"; 
        
        public static final String TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_FILE = "There was a problem trying to prepareAndExecuteQuery the file";
        
        public static final String TECHNICAL_PROBLEM_SETPARAMETERSVALUES_FILE = "There was a problem trying to setParametersValues the file";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_SETPARAMETERSVALUES_FILE= "There was a unexpected problem trying to setParametersValues the file";
        
        public static final String TECHNICAL_PROBLEM_EXECUTEQUERY_FILE = "There was a problem trying to executeQuery the file";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTEQUERY_FILE = "There was a unexpected problem trying to executeQuery the file";
        
    }
    public static class FileTypePostgresqlDAO{
        private FileTypePostgresqlDAO () {
            super();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_FILETYPE = "There was a problem trying to create the filetype";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_FILETYPE = "There was a unexpected problem trying to create the filetype";
        public static final String TECHNICAL_PROBLEM_UPDATE_FILETYPE = "There was a problem trying to update the filetype";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_FILETYPE= "There was a unexpected problem trying to update the filetype";
        public static final String TECHNICAL_PROBLEM_DELETE_FILETYPE = "There was a problem trying to delete the filetype";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_FILETYPE = "There was a unexpected problem trying to delete the filetype";
        
        public static final String TECHNICAL_PROBLEM_FILLRESULTS_FILETYPE = "There was a problem trying to fillResults the filetype";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILLRESULTS_FILETYPE = "There was a unexpected problem trying to fillResults the filetype";
        
        public static final String TECHNICAL_PROBLEM_FILLFILETYPEDTO_FILETYPE ="There was a problem trying to fillFileTypeDTO the filetype"; 
        
        public static final String TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_FILETYPE = "There was a problem trying to prepareAndExecuteQuery the filetype";
        
        public static final String TECHNICAL_PROBLEM_SETPARAMETERSVALUES_FILETYPE = "There was a problem trying to setParametersValues the filetype";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_SETPARAMETERSVALUES_FILETYPE = "There was a unexpected problem trying to setParametersValues the filetype";
        
        public static final String TECHNICAL_PROBLEM_EXECUTEQUERY_FILETYPE = "There was a problem trying to executeQuery the filetype";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTEQUERY_FILETYPE = "There was a unexpected problem trying to executeQuery the filetype";
    }
    public static class LikePostgresqlDAO{
        private LikePostgresqlDAO () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_LIKE = "There was a problem trying to create the like";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_LIKE = "There was a unexpected problem trying to create the like";
        public static final String TECHNICAL_PROBLEM_DELETE_LIKE= "There was a problem trying to delete the like";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_LIKE= "There was a unexpected problem trying to delete the like";
        
        public static final String TECHNICAL_PROBLEM_FILLRESULTS_LIKE = "There was a problem trying to fillResults the like";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILLRESULTS_LIKE = "There was a unexpected problem trying to fillResults the like";
        
        public static final String TECHNICAL_PROBLEM_FILLLIKEDTO_LIKE = "There was a unexpected problem trying to fillLikeDTO the like";
        
        public static final String TECHNICAL_PROBLEM_FILLLIKEUSERDTO_LIKE = "There was a unexpected problem trying to fillLikeUserDTO the like";
        
        public static final String TECHNICAL_PROBLEM_FILLARTWORKDTO_LIKE = "There was a unexpected problem trying to fillArtworkDTO the like";
        
        public static final String TECHNICAL_PROBLEM_FILLFILEDTO_LIKE ="There was a problem trying to fillFileDTO the like";
        public static final String TECHNICAL_PROBLEM_FILLFILETYPEDTO_LIKE ="There was a problem trying to fillFileTypeDTO the like";
        
        public static final String TECHNICAL_PROBLEM_FILLARTWORKTYPEDTO_LIKE = "There was a problem trying to fillArtworkTypeDTO the like";
        
        public static final String TECHNICAL_PROBLEM_FILLUSERDTO_LIKE = "There was a problem trying to fillUserDTO the like";
        
        public static final String TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_LIKE = "There was a problem trying to prepareAndExecuteQuery the like";
        
        public static final String TECHNICAL_PROBLEM_SETPARAMETERSVALUES_LIKE = "There was a problem trying to setParametersValues the like";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_SETPARAMETERSVALUES_LIKE = "There was a unexpected problem trying to setParametersValues the like";
        
        public static final String TECHNICAL_PROBLEM_EXECUTEQUERY_LIKE = "There was a problem trying to executeQuery the like";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTEQUERY_LIKE = "There was a unexpected problem trying to executeQuery the like";
    }
    public static class UserPostgresqlDAO {
        private UserPostgresqlDAO () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_USER = "There was a problem trying to create the user";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_USER= "There was a unexpected problem trying to create the user";
        public static final String TECHNICAL_PROBLEM_UPDATE_USER = "There was a problem trying to update the user";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_USER = "There was a unexpected problem trying to update the user";
        public static final String TECHNICAL_PROBLEM_DELETE_USER = "There was a problem trying to delete the user";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_USER = "There was a unexpected problem trying to delete the user";
        
        public static final String TECHNICAL_PROBLEM_FILLUSERDTO_USER = "There was a problem trying to fillUserDTO the user";
        
        public static final String TECHNICAL_PROBLEM_FILLRESULTS_USER = "There was a problem trying to fillResults the user";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FILLRESULTS_USER = "There was a unexpected problem trying to fillResults the user";
        
        
        public static final String TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_USER = "There was a problem trying to prepareAndExecuteQuery the user";
        
        public static final String TECHNICAL_PROBLEM_SETPARAMETERSVALUES_USER = "There was a problem trying to setParametersValues the user";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_SETPARAMETERSVALUES_USER= "There was a unexpected problem trying to setParametersValues the user";
        
        public static final String TECHNICAL_PROBLEM_EXECUTEQUERY_USER = "There was a problem trying to executeQuery the user";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_EXECUTEQUERY_USER  = "There was a unexpected problem trying to executeQuery the user";
    }
    public static class CreateCategoryArtworkCommandImpl {
        private CreateCategoryArtworkCommandImpl() {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_CATEGORYARTWORK = "There was a problem trying to create the artwork";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_CATEGORYARTWORK= "There was a unexpected problem trying to create the categoryArtwork";
        
    }
    public static class CreateFileCommandImpl {
        private CreateFileCommandImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_FILE = "There was a problem trying to create the file";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_FILE= "There was a unexpected problem trying to create the file";
        
    }
    public static class CreateLikeCommandImpl {
        private CreateLikeCommandImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_LIKE = "There was a problem trying to create the like";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_LIKE = "There was a unexpected problem trying to create the like";
        
    }
    public static class CreateUserCommandImpl {
        private CreateUserCommandImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_USER = "There was a problem trying to create the user";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_USER = "There was a unexpected problem trying to create the user";
        
    }
    public static class DeleteLikeCommandImpl{
        private DeleteLikeCommandImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_DELETELIKE = "There was a problem trying to delete the deletelike";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_DELETELIKE = "There was a unexpected problem trying to delete the deletelike";
         
    }
    public static class FindAllArtworkTypeCommandImpl {
        private FindAllArtworkTypeCommandImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_FIND_ARTWORKTYPE = "There was a problem trying to find the artworktype";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FIND_ARTWORKTYPE ="There was a unexpected problem trying to find the artworktype";
    
    }
    public static class FindAllCategoryCommandImpl {
        private FindAllCategoryCommandImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_FIND_CATEGORY = "There was a problem trying to find the category";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FIND_CATEGORY ="There was a unexpected problem trying to find the category";
    
    }
    public static class FindArtworkByIdCommandImpl {
        private FindArtworkByIdCommandImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_FIND_ARTWORKBYID = "There was a problem trying to find the ArtworkById";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FIND_ARTWORKBYID ="There was a unexpected problem trying to find the ArtworkById";
    
    }
    public static class FindArtworktypeByIdCommandImpl {
        private FindArtworktypeByIdCommandImpl () {
        }
        public static final String TECHNICAL_PROBLEM_FIND_ARTWORKTYPEBYID = "There was a problem trying to find the ArtworkTypeById";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FIND_ARTWORKTYPEBYID ="There was a unexpected problem trying to find the ArtworkTypeById";
    
    }
    public static class FindCategoryByIdCommandImpl {
        private FindCategoryByIdCommandImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_FIND_CATEGORYBYID = "There was a problem trying to find the CategoryById";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FIND_CATEGORYBYID ="There was a unexpected problem trying to find the CategoryById";
    
    }
    public static class FindFileTypeByNameCommandImpl {
        private FindFileTypeByNameCommandImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_FIND_FILETYPEBYNAME = "There was a problem trying to find the FileTypeByName";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FIND_FILETYPEBYNAME ="There was a unexpected problem trying to find the FileTypeByName";
           
    }
    public static class FindUserByIdCommandImpl{
        private FindUserByIdCommandImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_FIND_USERBYID = "There was a problem trying to find the UserById";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FIND_USERBYID="There was a unexpected problem trying to find the UserById";
        
    }
    public static class PostArtworkCommandImpl {
        private PostArtworkCommandImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_POST_ARTWORK = "There was a problem trying to post the Artwork";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_POST_ARTWORK ="There was a unexpected problem trying to post the Artwork";       
    }
    
    public static class CreateArtworkUsecaseImpl {
        private CreateArtworkUsecaseImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_ARTWORK = "There was a problem trying to create the artwork";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_ARTWORK ="There was a unexpected problem trying to create the artwork";
        
        public static final String TECHNICAL_PROBLEM_CREATE_VALIDATETITTLE = "The tittle of the work cannot be empty";
        public static final String TECHNICAL_PROBLEM_CREATE_FINDARTWORKTYPE = "The selected artwork type does not exist";
        public static final String TECHNICAL_PROBLEM_CREATE_FINDUSER = "User does not exist";
    }
    public static class CreateCategoryArtworkUsecaseImpl{
        private CreateCategoryArtworkUsecaseImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_FINDARTWORK= "The work does not exist";
        public static final String TECHNICAL_PROBLEM_CREATE_FINDCATEOGRY= "The cateogry does not exist";
        
    }
    public static class CreateFileUsecaseImpl {
        private CreateFileUsecaseImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_FINDFILETYPE= "File type does no exist";
    }
    public static class CreateLikeUsecaseImpl {
        private CreateLikeUsecaseImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_LIKE= "There was a problem trying to create the Like"; 
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_LIKE="There was a unexpected problem trying to create the Like"; 
       
        public static final String TECHNICAL_PROBLEM_REGISTRE_LIKE= "The like has been successfully registered";
    }
    public static class CreateUserUsecaseImpl {
        private CreateUserUsecaseImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_USER= "There was a problem trying to create the User"; 
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_CREATE_USER="There was a unexpected problem trying to create the User"; 
        
        public static final String TECHNICAL_PROBLEM_CREATE_INVALIDUSERNAME= "The username entered is offensive";
        
        public static final String TECHNICAL_PROBLEM_CREATE_VALIDATENAME = "The name cannot be empty";
        public static final String TECHNICAL_PROBLEM_CREATE_VALIDATELASTNAME = "The second name cannot be empty";
        public static final String TECHNICAL_PROBLEM_CREATE_VALIDATEMAIL = "It is not formatted properly when creating an email";
        public static final String TECHNICAL_PROBLEM_CREATE_VALIDATPASSWORD = "The password field cannot be empty";
        public static final String TECHNICAL_PROBLEM_CREATE_VALIDATEUSERNAME = "Unable to enter username";
        public static final String TECHNICAL_PROBLEM_CREATE_VALIDATEUSERNAME2 = "Username field cannot remain empty";
        public static final String TECHNICAL_PROBLEM_CREATE_VALIDATEUSERMANE3 = "The username entered already exists";
    }
    public static class ArtworkController {
        private ArtworkController () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_CREATE_ARTWORK = "The work was created correctly";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FATALERROR  = "An unexpected error occurred while creating the work";     
        public static final String TECHNICAL_PROBLEM_FAIL_ARTWORK = "An error occurred trying to save the work on the server";
        public static final String SUCCESS_FIND_ARTWORK = "The works stored were returned";
        public static final String SUCCESS_SAVE_FILE = "The file was saved";
        public static final String THECNICAL_PROBLEM_CREATE_FILE = "The file has a problem";
        public static final String FATAL_PROBLEM_CREATE_FILE = "theres was a FATAL problem to create File";


    }
    public static class ArtworkTypeController{
        private ArtworkTypeController () {
            super ();
        }
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FATALERROR  = "An unexpected error occurred while creating the artworktype";
        public static final String SUCCESS_FIND_ARTWORKTYPE = "The types of works stored were returned";
    }
    public static class CategoryController{
        private CategoryController () {
            super ();
        }
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FATALERROR  = "An unexpected error occurred while creating the Category";
        public static final String SUCCESS_FIND_CATEGORY = "Stored categories were returned";
    }
    public static class LikeController {
        private LikeController () {
            super ();
        }
        public static final String SUCCESS_REGISTRE_LIKE = "The Like was registered correctly";
    }
    public static class UserController{
        private UserController () {
            super ();
        }
        public static final String SUCCESS_CREATE_USER = "The user has been created successfully";
        public static final String TECHNICAL_PROBLEM_CREATE_USER = "An error occurred in creating the budget, please try again.......";
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FATALERROR  = "An unexpected error occurred while creating the User";
    }
    public static class CreateArtworkValidator {
        private CreateArtworkValidator () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_VALIDATE_ARTWORKTYPE = "You must assing a tittle to the work";
        public static final String SUCCESS_SAVE_FILE = "the file was success save";
 

        public static final String TECHNICAL_PROBLEM_CREATE_VALIDATEARTWORKTITTLE = "You must assing a tittle to the work";
    }
    public static class CreateUserValidator {
        private CreateUserValidator () {
            super ();
        }
        public static final String INVALID_MAIL = "Invalid email";
        
    }
    public static class DeleteLikeUsecaseImpl {
        private DeleteLikeUsecaseImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_DELETE_LIKE= "There was a problem trying to delete the Like"; 
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_DELETE_LIKE ="There was a unexpected problem trying to delete the like";
       
    }
    public static class FindAllArtworkCommandImpl {
        private FindAllArtworkCommandImpl () {
            super ();
        }
        public static final String TECHNICAL_PROBLEM_FIND_ARTWORK= "There was a problem trying to find the artworks"; 
        public static final String TECHNICAL_UNEXPECTED_PROBLEM_FIND_ARTWORK ="There was a unexpected problem trying to find the artworks";
       
    }
}