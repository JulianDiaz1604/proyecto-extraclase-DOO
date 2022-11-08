package edu.uco.artdly.data.daofactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.uco.artdly.crosscutting.exception.data.CrosscuttingCustomException;
import edu.uco.artdly.data.dao.ArtworkDAO;
import edu.uco.artdly.data.dao.ArtworkTypeDAO;
import edu.uco.artdly.data.dao.CategoryArtworkDAO;
import edu.uco.artdly.data.dao.CategoryDAO;
import edu.uco.artdly.data.dao.CommentDAO;
import edu.uco.artdly.data.dao.FileDAO;
import edu.uco.artdly.data.dao.LikeDAO;
import edu.uco.artdly.data.dao.UserDAO;
import edu.uco.artdly.data.dao.relational.postgresql.ArtworkPostgresqlDAO;
import edu.uco.artdly.data.dao.relational.postgresql.ArtworkTypePostgresqlDAO;
import edu.uco.artdly.data.dao.relational.postgresql.CategoryArtworkPostgresqlDAO;
import edu.uco.artdly.data.dao.relational.postgresql.CategoryPostgresqlDAO;
import edu.uco.artdly.data.dao.relational.postgresql.CommentPostgresqlDAO;
import edu.uco.artdly.data.dao.relational.postgresql.FilePostgresqlDAO;
import edu.uco.artdly.data.dao.relational.postgresql.LikePostgresqlDAO;
import edu.uco.artdly.data.dao.relational.postgresql.UserPostgresqlDAO;

public class PostgreSQLDAOFactory extends DAOFactory {

    private Connection connection;

    public PostgreSQLDAOFactory(){
        openConnection();
    }

    //TODO - probar conexion
    @Override
    protected void openConnection() {
        final String url = "postgres://ohqbpnkwqkxoly:fa44911ab8a2ae0393cc9d572c147ecdc3b36d77228d2059351ecccebbf45bcb@ec2-44-209-158-64.compute-1.amazonaws.com:5432/d6be0iacrla2jt";
        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initTransaction() {
        try {
            edu.uco.artdly.crosscutting.helper.PostgresqlConnectionHelper.initTrasaction(connection);
        } catch (CrosscuttingCustomException e) {
            // TODO: handle exception
        }
    }

    @Override
    public void confirmTransaction() {
        try {
            edu.uco.artdly.crosscutting.helper.PostgresqlConnectionHelper.commitTrasaction(connection);
        } catch (CrosscuttingCustomException e) {
            // TODO: handle exception
        }
    }

    @Override
    public void closeConection() {
        try {
            edu.uco.artdly.crosscutting.helper.PostgresqlConnectionHelper.closeConnection(connection);
        } catch (CrosscuttingCustomException e) {
            // TODO: handle exception
        }
    }

    @Override
    public void cancelTransaction() {
        try {
            edu.uco.artdly.crosscutting.helper.PostgresqlConnectionHelper.rollbackTrasaction(connection);
        } catch (CrosscuttingCustomException e) {
            // TODO: handle exception
        }
    }

    @Override
    public ArtworkDAO getArtworkDAO() {
        return new ArtworkPostgresqlDAO(connection);
    }

    @Override
    public ArtworkTypeDAO getArtworkTypeDAO() {
        return new ArtworkTypePostgresqlDAO(connection);
    }

    @Override
    public CategoryArtworkDAO getCategoryArtworkDAO() {
        return new CategoryArtworkPostgresqlDAO(connection);
    }

    @Override
    public CategoryDAO getCategoryDAO() {
        return new CategoryPostgresqlDAO(connection);
    }

    @Override
    public CommentDAO getCommentDAO() {
        return new CommentPostgresqlDAO(connection);
    }

    @Override
    public FileDAO getFileDAO() {
        return new FilePostgresqlDAO(connection);
    }

    @Override
    public LikeDAO getLikeDAO() {
        return new LikePostgresqlDAO(connection);
    }

    @Override
    public UserDAO getUserDAO() {
        return new UserPostgresqlDAO(connection);
    }
    
}
