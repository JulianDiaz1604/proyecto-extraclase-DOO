package edu.uco.artdly.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.data.DataCustomException;
import edu.uco.artdly.crosscutting.helper.DateHelper;
import edu.uco.artdly.crosscutting.helper.MailHelper;
import edu.uco.artdly.crosscutting.helper.ObjectHelper;
import edu.uco.artdly.crosscutting.helper.StringHelper;
import edu.uco.artdly.crosscutting.helper.UUIDHelper;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.dao.UserDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.UserDTO;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class UserPostgresqlDAO  extends DAORelational implements UserDAO {

	public UserPostgresqlDAO(Connection connection) {
		super(connection);
	
	}

	@Override
	public void create(UserDTO user) {
		final var sql = "INSERT INTO public.user(id, name, last_name, mail, username, password, birth_date, description, is_private) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            
            preparedStatement.setString(1, user.getIdAsString());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getMail());
            preparedStatement.setString(5, user.getUsername());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.setDate(7, user.getBirthDate());
            preparedStatement.setString(8, user.getDescription());
            preparedStatement.setBoolean(9, user.isPrivate());
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.UserPostgresqlDAO.TECHNICAL_PROBLEM_CREATE_USER,exception); 
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.UserPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_USER, exception); 
        }
		
	}

	@Override
	public List<UserDTO> find(UserDTO user) {

        var parameters = new ArrayList<Object>();
        final var sqlBuilder = new StringBuilder();

        createSelectFrom(sqlBuilder);
        createWhere(sqlBuilder, user, parameters);
        createOrderBy(sqlBuilder);

        return prepareAndExecuteQuery(sqlBuilder, parameters);
	}

    private final List<UserDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters) {

        try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())) {
            
            setParametersValues(preparedStatement, parameters);

            return executeQuery(preparedStatement);

        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.UserPostgresqlDAO.TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_USER, exception);
        }

    }


    private void setParametersValues(PreparedStatement preparedStatement, List<Object> parameters) {
        try {
            for(int index = 0; index < parameters.size(); index++){
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.UserPostgresqlDAO.TECHNICAL_PROBLEM_SETPARAMETERSVALUES_USER, exception); 
        } catch(final Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.UserPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_SETPARAMETERSVALUES_USER, exception); 
        }
    }


    private final void createSelectFrom(final StringBuilder sqlBuilder){
        /*
        sqlBuilder.append("SELECT usr.id AS UserId, ");
        sqlBuilder.append("       usr.name AS UserName, ");
        sqlBuilder.append("       usr.lastName AS UserLastName, ");
        sqlBuilder.append("       usr.mail AS UserMail, ");
        sqlBuilder.append("       usr.username AS UserNickname, ");
        sqlBuilder.append("       usr.password AS UserPassword, ");
        sqlBuilder.append("       usr.birthDate AS UserBirthdate, ");
        sqlBuilder.append("       usr.description AS UserDescription, ");
        sqlBuilder.append("       usr.is_Private AS UserIsPrivate ");
        sqlBuilder.append("FROM user usr ");
        */
        sqlBuilder.append("SELECT id AS UserId, ");
        sqlBuilder.append("       name AS UserName, ");
        sqlBuilder.append("       last_name AS UserLastName, ");
        sqlBuilder.append("       mail AS UserMail, ");
        sqlBuilder.append("       username AS UserNickname, ");
        sqlBuilder.append("       password AS UserPassword, ");
        sqlBuilder.append("       birth_date AS UserBirthdate, ");
        sqlBuilder.append("       description AS UserDescription, ");
        sqlBuilder.append("       is_private AS UserIsPrivate ");
        sqlBuilder.append("FROM public.user ");
}
    private final void createWhere(final StringBuilder sqlBuilder, final UserDTO user, final List<Object> parameters){

        var setWhere = true;

        if(!ObjectHelper.isNull(user)){

            if (!UUIDHelper.isDefaultUUID(user.getId())){
                sqlBuilder.append("WHERE id = ? ");
                setWhere = false;
                parameters.add(user.getIdAsString());
            }/*
            if (!ObjectHelper.isNull(user.getName())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("name = ? ");
                setWhere = false;
                parameters.add(user.getName());
            }
            if (!ObjectHelper.isNull(user.getLastName())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("last_name = ? ");
                parameters.add(user.getLastName());
            }
            if (!ObjectHelper.isNull(user.getMail())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("mail = ? ");
                setWhere = false;
                parameters.add(user.getMail());
            }
            if (!ObjectHelper.isNull(user.getUsername())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("username = ? ");
                setWhere = false;
                parameters.add(user.getUsername());
            }
            if (!ObjectHelper.isNull(user.getPassword())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("password = ? ");
                setWhere = false;
                parameters.add(user.getPassword());
            }
            if (!ObjectHelper.isNull(user.getBirthDate())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("birth_date = ? ");
                setWhere = false;
                parameters.add(user.getBirthDate());
            }
            if (!ObjectHelper.isNull(user.getDescription())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("description = ? ");
                setWhere = false;
                parameters.add(user.getDescription());
            }
            if (!ObjectHelper.isNull(user.isPrivate())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("is_private = ? ");
                parameters.add(user.isPrivate());
            } */
            if (!StringHelper.isDefaultString(user.getName())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("name = ? ");
                setWhere = false;
                parameters.add(user.getName());
            }
            if (!StringHelper.isDefaultString(user.getLastName())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("last_name = ? ");
                parameters.add(user.getLastName());
            }
            if (!MailHelper.isDefaultMail(user.getMail())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("mail = ? ");
                setWhere = false;
                parameters.add(user.getMail());
            }
            if (!StringHelper.isDefaultString(user.getUsername())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("username = ? ");
                setWhere = false;
                parameters.add(user.getUsername());
            }
            if (!StringHelper.isDefaultString(user.getPassword())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("password = ? ");
                setWhere = false;
                parameters.add(user.getPassword());
            }
            if (!DateHelper.isDefaultDate(user.getBirthDate())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("birth_date = ? ");
                setWhere = false;
                parameters.add(user.getBirthDate());
            }
            if (!StringHelper.isDefaultString(user.getDescription())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("description = ? ");
                setWhere = false;
                parameters.add(user.getDescription());
            }
            if (!ObjectHelper.isNull(user.isPrivate())){
                sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("is_private = ? ");
                parameters.add(user.isPrivate());
            }

            

        }

    }

    private final void createOrderBy(final StringBuilder sqlBuilder){

        sqlBuilder.append("ORDER BY id"); 


    }

    private final List<UserDTO> executeQuery(PreparedStatement preparedStatement){

        try (final var resultSet = preparedStatement.executeQuery()) {
            return fillResults(resultSet);
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.UserPostgresqlDAO.TECHNICAL_PROBLEM_EXECUTEQUERY_USER, exception); 
        } catch(final DataCustomException exception) {
            throw exception;
        } catch(final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.UserPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECUTEQUERY_USER, exception); 
        }
    }

    private final List<UserDTO> fillResults(final ResultSet resultSet){

        try{

            var results = new ArrayList<UserDTO>();

            while(resultSet.next()){

                results.add(fillUserDTO(resultSet));

            }

            return results;

        } catch (final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.UserPostgresqlDAO.TECHNICAL_PROBLEM_FILLRESULTS_USER, exception); 
        } catch (final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.UserPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILLRESULTS_USER, exception); 
        }

    }



    private final UserDTO fillUserDTO(final ResultSet resultSet){

        try {

            return UserDTO.create(resultSet.getString("UserId"), 
                                    resultSet.getString("UserName"), 
                                    resultSet.getString("UserLastName"),
                                    resultSet.getString("UserMail"),
                                    resultSet.getString("UserNickname"),
                                    resultSet.getString("UserPassword"),
                                    resultSet.getDate("UserBirthdate"),
                                    resultSet.getString("UserDescription"),
                                    resultSet.getBoolean("UserIsPrivate"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.UserPostgresqlDAO.TECHNICAL_PROBLEM_FILLUSERDTO_USER, exception); 
        }

    }
//USER(id, Name, secondName, mail, username, password, birthDate, Description, esPrivado)
	
	@Override
	public void update(UserDTO user) {
		   final var sql = "UPDATE public.user SET id = ?, name = ?, last_name = ? , mail = ? , username = ? , password = ?, birth_date = ?, description = ?, is_private = ? ";

	        try (final var preparedStatement = getConnection().prepareStatement(sql)) {
	            
	            preparedStatement.setString(1, user.getIdAsString());
	            preparedStatement.setString(2, user.getName());
	            preparedStatement.setString(3, user.getLastName());
	            preparedStatement.setString(4, user.getMail());
	            preparedStatement.setString(5, user.getUsername());
	            preparedStatement.setString(6, user.getPassword());
	            preparedStatement.setDate(7, user.getBirthDate());
	            preparedStatement.setString(8, user.getDescription());
	            preparedStatement.setBoolean(9, user.isPrivate());
	            preparedStatement.executeUpdate();

	        } catch (SQLException exception) {
	            throw DataCustomException.CreateTechnicalException(Messages.UserPostgresqlDAO.TECHNICAL_PROBLEM_UPDATE_USER, exception); 
	        } catch (Exception exception) {
	            throw DataCustomException.CreateTechnicalException(Messages.UserPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_USER, exception); 
	        }
		
	}

	@Override
	public void delete(UUID id) {
        final var sql = "DELETE FROM public.user WHERE id = ?";

        try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            
            preparedStatement.setString(1, getUUIDAsString(id));

            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.UserPostgresqlDAO.TECHNICAL_PROBLEM_DELETE_USER, exception); 
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.UserPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_USER, exception); 
        }
		
	}

}
