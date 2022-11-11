package edu.uco.artdly.data.daofactory;

import edu.uco.artdly.crosscutting.exception.data.DataCustomException;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.dao.ArtworkDAO;
import edu.uco.artdly.data.dao.ArtworkTypeDAO;
import edu.uco.artdly.data.dao.CategoryArtworkDAO;
import edu.uco.artdly.data.dao.CategoryDAO;
import edu.uco.artdly.data.dao.CommentDAO;
import edu.uco.artdly.data.dao.FileDAO;
import edu.uco.artdly.data.dao.FileTypeDAO;
import edu.uco.artdly.data.dao.LikeDAO;
import edu.uco.artdly.data.dao.UserDAO;
import edu.uco.artdly.data.enumeration.DAOFactoryType;

public abstract class DAOFactory {

    public static final DAOFactory getDAOFactory(DAOFactoryType factory){

        DAOFactory daoFactory;

        switch(factory){
            case SQLSERVER: 
                throw DataCustomException.CreateTechnicalException(Messages.DAOFactory.TECHNICAL_SQLSERVER_NOT_IMPLEMENTED);
            case CASSANDRA:    
                throw DataCustomException.CreateTechnicalException(Messages.DAOFactory.TECHNICAL_CASSANDRA_NOT_IMPLEMENTED);
            case MARIADB: 
                throw DataCustomException.CreateTechnicalException(Messages.DAOFactory.TECHNICAL_MARIADB_NOT_IMPLEMENTED) ;
            case MONGODB: 
                throw DataCustomException.CreateTechnicalException(Messages.DAOFactory.TECHNICAL_MONGODB_NOT_IMPLEMENTED) ;
            case MYSQL: 
                throw DataCustomException.CreateTechnicalException(Messages.DAOFactory.TECHNICAL_MYSQL_NOT_IMPLEMENTED) ;
            case ORACLE: 
                throw DataCustomException.CreateTechnicalException(Messages.DAOFactory.TECHNICAL_ORACLE_NOT_IMPLEMENTED) ;
            case POSTGRESQL: 
                daoFactory = new PostgreSQLDAOFactory();
                break;
            default: 
                throw DataCustomException.CreateTechnicalException(Messages.DAOFactory.TECHNICAL_UNEXPECTED_DAOFACTORY) ;
        }

        return daoFactory;
    }
    
    protected abstract void openConnection();

    public abstract void initTransaction();

    public abstract void confirmTransaction();

    public abstract void closeConection();
    
    public abstract void cancelTransaction();

    public abstract ArtworkDAO getArtworkDAO();

    public abstract ArtworkTypeDAO getArtworkTypeDAO();

    public abstract CategoryArtworkDAO getCategoryArtworkDAO();

    public abstract CategoryDAO getCategoryDAO();

    public abstract CommentDAO getCommentDAO();

    public abstract FileDAO getFileDAO();

    public abstract FileTypeDAO getFileTypeDAO();

    public abstract LikeDAO getLikeDAO();

    public abstract UserDAO getUserDAO();

}
