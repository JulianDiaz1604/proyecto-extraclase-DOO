package edu.uco.artdly.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.data.DataCustomException;
import edu.uco.artdly.crosscutting.helper.ObjectHelper;
import edu.uco.artdly.crosscutting.helper.UUIDHelper;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.dao.FileTypeDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.FileTypeDTO;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class FileTypePostgresqlDAO extends DAORelational implements FileTypeDAO {

	public FileTypePostgresqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(FileTypeDTO filetype) {
		final var sql = "INSERT INTO filetype (id, fileType) VALUES (?, ?)";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			
			preparedStatement.setString(1, filetype.getIdAsString());
			preparedStatement.setString(2, filetype.getFileType());
			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw DataCustomException.CreateTechnicalException(Messages.FileTypePostgresqlDAO.TECHNICAL_PROBLEM_CREATE_FILETYPE, exception); 
		} catch (Exception exception) {
			throw DataCustomException.CreateTechnicalException(Messages.FileTypePostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_FILETYPE, exception); 
		}
	}

	@Override
	public List<FileTypeDTO> find(FileTypeDTO filetype) {
		
		var parameters = new ArrayList<Object>();
		final var sqlBuilder = new StringBuilder();

		createSelectFrom(sqlBuilder);
		createWhere(sqlBuilder, filetype, parameters);
		createOrderBy(sqlBuilder);

		return prepareAndExecuteQuery(sqlBuilder, parameters);
	}

	@Override
	public void update(FileTypeDTO filetype) {

		final var sql = "UPDATE filetype set id = ?, fileType = ? ";

		try(final var prepareStatement = getConnection().prepareStatement(sql)) {
			
			prepareStatement.setString(1, filetype.getIdAsString());
			prepareStatement.setString(2, filetype.getFileType());
			prepareStatement.executeUpdate();

		} catch (SQLException exception) {
			throw DataCustomException.CreateTechnicalException(Messages.FileTypePostgresqlDAO.TECHNICAL_PROBLEM_UPDATE_FILETYPE, exception); 
		} catch (Exception exception) {
			throw DataCustomException.CreateTechnicalException(Messages.FileTypePostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_FILETYPE, exception); 
		}
		
	}

	@Override
	public void delete(UUID id) {

		final var sql = "DELETE FROM filetype WHERE id = ?";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            
            preparedStatement.setString(1, getUUIDAsString(id));
			preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.FileTypePostgresqlDAO.TECHNICAL_PROBLEM_DELETE_FILETYPE, exception); 
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.FileTypePostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_FILETYPE, exception); 
        }
		
	}

	private final void createSelectFrom(final StringBuilder sqlBuilder){
		sqlBuilder.append("SELECT ft.id AS FileTypeId, ");
        sqlBuilder.append("       ft.fileType AS FileTypeName ");
		sqlBuilder.append("FROM filetype ft ");
	}

	private final void createWhere(final StringBuilder sqBuilder, final FileTypeDTO filetype, final List<Object> parameters){
		
		var setWhere = true;

		if(!ObjectHelper.isNull(filetype)){

			if(!UUIDHelper.isDefaultUUID(filetype.getId())){
				sqBuilder.append("WHERE id = ? ");
				setWhere = false;
				parameters.add(filetype.getIdAsString());
			}
			if(!ObjectHelper.isNull(filetype.getFileType())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("fileType = ? ");
				parameters.add(filetype.getFileType());
			}

		}
	}

	private final void createOrderBy(final StringBuilder sqlBuilder){

		sqlBuilder.append("ORDER BY ft.id");

	}

    private final List<FileTypeDTO> fillResults(final ResultSet resultSet){

        try{

            var results = new ArrayList<FileTypeDTO>();

            while(resultSet.next()){

                results.add(fillFileTypeDTO(resultSet));

            }

            return results;

        } catch (final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.FileTypePostgresqlDAO.TECHNICAL_PROBLEM_FILLRESULTS_FILETYPE, exception); 
        } catch (final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.FileTypePostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILLRESULTS_FILETYPE, exception); 
        }

    }

	private final FileTypeDTO fillFileTypeDTO(final ResultSet resultSet){

        try {

            return FileTypeDTO.create(resultSet.getString("FileTypeId"), 
                                  	  resultSet.getString("FileTypeName"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.FileTypePostgresqlDAO.TECHNICAL_PROBLEM_FILLFILETYPEDTO_FILETYPE, exception); 
        }

    }

    private final List<FileTypeDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters) {

        try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())) {
            
            setParametersValues(preparedStatement, parameters);

            return executeQuery(preparedStatement);

        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.FileTypePostgresqlDAO.TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_FILETYPE, exception);
        }

    }


    private void setParametersValues(PreparedStatement preparedStatement, List<Object> parameters) {
        try {
            for(int index = 0; index < parameters.size(); index++){
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.FileTypePostgresqlDAO.TECHNICAL_PROBLEM_SETPARAMETERSVALUES_FILETYPE, exception); 
        } catch(final Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.FileTypePostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_SETPARAMETERSVALUES_FILETYPE, exception); 
        }
    }

    private final List<FileTypeDTO> executeQuery(PreparedStatement preparedStatement){

        try (final var resultSet = preparedStatement.executeQuery()) {
            return fillResults(resultSet);
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.FileTypePostgresqlDAO.TECHNICAL_PROBLEM_EXECUTEQUERY_FILETYPE, exception); 
        } catch(final DataCustomException exception) {
            throw exception;
        } catch(final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.FileTypePostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECUTEQUERY_FILETYPE, exception); 
        }
    }

}
