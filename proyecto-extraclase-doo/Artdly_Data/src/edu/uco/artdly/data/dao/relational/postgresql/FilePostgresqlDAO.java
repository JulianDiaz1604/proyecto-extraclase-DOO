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
import edu.uco.artdly.data.dao.FileDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.FileDTO;
import edu.uco.artdly.domain.FileTypeDTO;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class FilePostgresqlDAO extends DAORelational implements FileDAO {

	public FilePostgresqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(FileDTO file) {
		final var sql = "INSERT INTO file (id, pathFile, typeFile) VALUES (?, ?, ?)";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			
			preparedStatement.setString(1, file.getIdAsString());
			preparedStatement.setString(2, file.getPathFile());
			preparedStatement.setString(3, file.getTypeFile().getIdAsString());
			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw DataCustomException.CreateTechnicalException(Messages.FilePostgresqlDAO.TECHNICAL_PROBLEM_CREATE_FILE, exception); 
		} catch (Exception exception) {
			throw DataCustomException.CreateTechnicalException(Messages.FilePostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_FILE, exception); 
		}
	}

	@Override
	public List<FileDTO> find(FileDTO file) {
		
		var parameters = new ArrayList<Object>();
		final var sqlBuilder = new StringBuilder();

		createSelectFrom(sqlBuilder);
		createWhere(sqlBuilder, file, parameters);
		createOrderBy(sqlBuilder);

		return prepareAndExecuteQuery(sqlBuilder, parameters);

	}

	@Override
	public void update(FileDTO file) {

		final var sql = "UPDATE file SET id = ?, pathFile = ?, typeFile = ? ";
		
		try (final var prepareStatement = getConnection().prepareStatement(sql)) {
			
			prepareStatement.setString(1, file.getIdAsString());
			prepareStatement.setString(2, file.getPathFile());
			prepareStatement.setString(3, file.getTypeFile().getIdAsString());
			prepareStatement.executeUpdate();

		} catch (SQLException exception) {
			throw DataCustomException.CreateTechnicalException(Messages.FilePostgresqlDAO.TECHNICAL_PROBLEM_UPDATE_FILE, exception); 
		} catch (Exception exception) {
			throw DataCustomException.CreateTechnicalException(Messages.FilePostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_FILE, exception); 
		}
		
		
	}

	@Override
	public void delete(UUID id) {
		
		final var sql = "DELETE FROM file WHERE id = ?";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            
            preparedStatement.setString(1, getUUIDAsString(id));
			preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.FilePostgresqlDAO.TECHNICAL_PROBLEM_DELETE_FILE, exception); 
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.FilePostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_FILE, exception); 
        }
		
	}

	private final void createSelectFrom(final StringBuilder sqlBuilder){
		sqlBuilder.append("SELECT fl.id AS FileId, ");
		sqlBuilder.append("       fl.pathFile AS FilePathFile, ");
        sqlBuilder.append("       fl.typeFileId AS FileTypeFile, ");
        sqlBuilder.append("       ft.fileType AS FileType ");
		sqlBuilder.append("FROM file fl ");
		sqlBuilder.append("JOIN filetype ft ON fl.typeFileId = ft.id ");
	}

	private final void createWhere(final StringBuilder sqBuilder, final FileDTO file, final List<Object> parameters){
		
		var setWhere = true;

		if(!ObjectHelper.isNull(file)){

			if(!UUIDHelper.isDefaultUUID(file.getId())){
				sqBuilder.append("WHERE id = ? ");
				setWhere = false;
				parameters.add(file.getIdAsString());
			}
			if(!ObjectHelper.isNull(file.getPathFile())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("pathFile = ? ");
				setWhere = false;
				parameters.add(file.getPathFile());
			}
			if(!ObjectHelper.isNull(file.getTypeFile().getId())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("typeFile = ? ");
				parameters.add(file.getTypeFile().getId());
			}

		}
	}

	private final void createOrderBy(final StringBuilder sqlBuilder){

		sqlBuilder.append("ORDER BY fl.id");

	}

    private final List<FileDTO> fillResults(final ResultSet resultSet){

        try{

            var results = new ArrayList<FileDTO>();

            while(resultSet.next()){

                results.add(fillFileDTO(resultSet));

            }

            return results;

        } catch (final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.FilePostgresqlDAO.TECHNICAL_PROBLEM_FILLRESULTS_FILE, exception); 
        } catch (final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.FilePostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILLRESULTS_FILE, exception); 
        }

    }

	private final FileDTO fillFileDTO(final ResultSet resultSet){

        try {

            return FileDTO.create(resultSet.getString("FileId"), 
                                  resultSet.getString("FilePathName"),
                                  fillFileTypeDTO(resultSet));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.FilePostgresqlDAO.TECHNICAL_PROBLEM_FILLFILEDTO_FILE, exception); 
        }

    }

	private final FileTypeDTO fillFileTypeDTO(final ResultSet resultSet){

        try {

            return FileTypeDTO.create(resultSet.getString("FileTypeFile"), 
                                  		 resultSet.getString("FileType"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.FilePostgresqlDAO.TECHNICAL_PROBLEM_FILLFILETYPEDTO_FILE, exception); 
        }

    }

    private final List<FileDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters) {

        try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())) {
            
            setParametersValues(preparedStatement, parameters);

            return executeQuery(preparedStatement);

        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.FilePostgresqlDAO.TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_FILE, exception);
        }

    }


    private void setParametersValues(PreparedStatement preparedStatement, List<Object> parameters) {
        try {
            for(int index = 0; index < parameters.size(); index++){
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.FilePostgresqlDAO.TECHNICAL_PROBLEM_SETPARAMETERSVALUES_FILE, exception); 
        } catch(final Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.FilePostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_SETPARAMETERSVALUES_FILE, exception); 
        }
    }

    private final List<FileDTO> executeQuery(PreparedStatement preparedStatement){

        try (final var resultSet = preparedStatement.executeQuery()) {
            return fillResults(resultSet);
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.FilePostgresqlDAO.TECHNICAL_PROBLEM_EXECUTEQUERY_FILE, exception); 
        } catch(final DataCustomException exception) {
            throw exception;
        } catch(final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.FilePostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECUTEQUERY_FILE, exception); 
        }
    }

}
