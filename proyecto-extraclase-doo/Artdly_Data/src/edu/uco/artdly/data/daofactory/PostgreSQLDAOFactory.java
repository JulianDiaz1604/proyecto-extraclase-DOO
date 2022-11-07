package edu.uco.artdly.data.daofactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.uco.artdly.data.dao.ArtworkDAO;
import edu.uco.artdly.data.dao.ArtworkTypeDAO;
import edu.uco.artdly.data.dao.CategoryArtworkDAO;
import edu.uco.artdly.data.dao.CategoryDAO;
import edu.uco.artdly.data.dao.CommentDAO;
import edu.uco.artdly.data.dao.FileDAO;
import edu.uco.artdly.data.dao.LikeDAO;
import edu.uco.artdly.data.dao.UserDAO;

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
            
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void confirmTransaction() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void closeConection() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void cancelTransaction() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public ArtworkDAO getArtworkDAO() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArtworkTypeDAO getArtworkTypeDAO() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CategoryArtworkDAO getCategoryArtworkDAO() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CategoryDAO getCategoryDAO() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CommentDAO getCommentDAO() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public FileDAO getFileDAO() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public LikeDAO getLikeDAO() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public UserDAO getUserDAO() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
