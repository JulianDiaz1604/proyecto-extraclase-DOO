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
import edu.uco.artdly.data.dao.CommentDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.domain.CommentDTO;
import edu.uco.artdly.domain.FileDTO;
import edu.uco.artdly.domain.FileTypeDTO;
import edu.uco.artdly.domain.UserDTO;

import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class CommentPostgresqlDAO extends DAORelational implements CommentDAO {

	public CommentPostgresqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(CommentDTO comment) {

		final var sql = "INSERT INTO comment (id, realizationDate, description, userID, artworkId) VALUES (?, ?, ?, ?, ?)";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			
			preparedStatement.setString(1, comment.getIdAsString());
			preparedStatement.setString(2, comment.getRealizationDate().toString());
			preparedStatement.setString(3, comment.getDescription());
			preparedStatement.setString(4, comment.getUser().getIdAsString());
			preparedStatement.setString(5, comment.getArtwork().getIdAsString());
			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_PROBLEM_CREATE_COMMENT, exception); 
		} catch (Exception exception) {
			throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_COMMENT, exception); 
		}
		
	}

	@Override
	public List<CommentDTO> find(CommentDTO comment) {
		
		var parameters = new ArrayList<Object>();
		final var sqlBuilder = new StringBuilder();

		createSelectFrom(sqlBuilder);
		createWhere(sqlBuilder, comment, parameters);
		createOrderBy(sqlBuilder);

		return prepareAndExecuteQuery(sqlBuilder, parameters);
		
	}


	@Override
	public void delete(UUID id) {
		
		final var sql = "DELETE FROM comment WHERE id = ?";

		try (final var prepareStatement = getConnection().prepareStatement(sql)) {
            
            prepareStatement.setString(1, getUUIDAsString(id));
			prepareStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_PROBLEM_DELETE_COMMENT, exception); 
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_COMMENT, exception); 
        }
		
	}

	private final void createSelectFrom(final StringBuilder sqlBuilder){
		sqlBuilder.append("SELECT com.id AS CommentId, ");
		sqlBuilder.append("       com.realizationDate AS CommentRealizationDate, ");
		sqlBuilder.append("       com.description AS CommentDescription, ");
		sqlBuilder.append("       com.userId AS CommentUserId, ");
		sqlBuilder.append("       usr1.name AS CommentUserName, ");
        sqlBuilder.append("       usr1.lastName AS CommentUserLastName, ");
        sqlBuilder.append("       usr1.mail AS CommentUserMail, ");
        sqlBuilder.append("       usr1.username AS CommentUserNickname, ");
        sqlBuilder.append("       usr1.password AS CommentUserPassword, ");
        sqlBuilder.append("       usr1.birthDate AS CommentUserBirthdate, ");
        sqlBuilder.append("       usr1.description AS CommentUserDescription, ");
        sqlBuilder.append("       usr1.isPrivate AS CommentUserIsPrivate ");
		sqlBuilder.append("       com.artworkId AS CommentArtworkId, ");
		sqlBuilder.append("       art.tittle AS ArtworkTittle, ");
		sqlBuilder.append("       art.description AS ArtworkDescription, ");
		sqlBuilder.append("       art.publicationDate AS ArtworkPublicationDate, ");
		sqlBuilder.append("       art.fileId AS FileId, ");
		sqlBuilder.append("       fil.pathFile AS FilePathFile, ");
		sqlBuilder.append("       fil.typeFileId AS FileTypeFileId, ");
		sqlBuilder.append("       fit.fileType AS FileTypeName, ");
		sqlBuilder.append("       art.artworkTypeId AS ArtworkTypeId, ");
		sqlBuilder.append("       aty.name AS ArtworkTypeName, ");
		sqlBuilder.append("       art.userId AS ArtworkUserId, ");
		sqlBuilder.append("       usr2.name AS UserName, ");
        sqlBuilder.append("       usr2.lastName AS UserLastName, ");
        sqlBuilder.append("       usr2.mail AS UserMail, ");
        sqlBuilder.append("       usr2.username AS UserNickname, ");
        sqlBuilder.append("       usr2.password AS UserPassword, ");
        sqlBuilder.append("       usr2.birthDate AS UserBirthdate, ");
        sqlBuilder.append("       usr2.description AS UserDescription, ");
        sqlBuilder.append("       usr2.isPrivate AS UserIsPrivate ");
		sqlBuilder.append("FROM comment com ");
		sqlBuilder.append("JOIN user usr1 ON com.userId = usr1.id ");
		sqlBuilder.append("JOIN artwork art ON com.artworkId = art.id ");
		sqlBuilder.append("JOIN file fil ON art.fileId = fil.id ");
		sqlBuilder.append("JOIN filetype fit ON fil.typeFileId = fit.id ");
		sqlBuilder.append("JOIN artworktype aty ON art.artworkTypeId = aty.id ");
		sqlBuilder.append("JOIN user usr2 ON art.userId = usr2.id ");
	}

	private final void createWhere(final StringBuilder sqBuilder, final CommentDTO comment, final List<Object> parameters){
		
		var setWhere = true;

		if(!ObjectHelper.isNull(comment)){

			if(!UUIDHelper.isDefaultUUID(comment.getId())){
				sqBuilder.append("WHERE id = ? ");
				setWhere = false;
				parameters.add(comment.getIdAsString());
			}
			if(!ObjectHelper.isNull(comment.getRealizationDate())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("realizationDate = ? ");
				setWhere = false;
				parameters.add(comment.getRealizationDate());
			}
			if(!ObjectHelper.isNull(comment.getDescription())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("description = ? ");
				setWhere = false;
				parameters.add(comment.getDescription());
			}
			if(!ObjectHelper.isNull(comment.getUser().getIdAsString())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("userId = ? ");
				setWhere = false;
				parameters.add(comment.getUser().getIdAsString());
			}
			if(!ObjectHelper.isNull(comment.getArtwork().getId())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("artworkId = ? ");
				parameters.add(comment.getArtwork().getIdAsString());
			}

		}
	}

	private final void createOrderBy(final StringBuilder sqlBuilder){

		sqlBuilder.append("ORDER BY com.id");

	}

    private final List<CommentDTO> fillResults(final ResultSet resultSet){

        try{

            var results = new ArrayList<CommentDTO>();

            while(resultSet.next()){

                results.add(fillCommentDTO(resultSet));

            }

            return results;

        } catch (final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_PROBLEM_FILLRESULTS_COMMENT, exception); 
        } catch (final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILLRESULTS_COMMENT, exception); 
        }

    }

	private final CommentDTO fillCommentDTO(final ResultSet resultSet){

		try {
			return CommentDTO.create(resultSet.getString("CommentId"), 
									 resultSet.getDate("CommentRealizationDate"), 
									 resultSet.getString("CommentDescription"), 
									 fillCommentUserDTO(resultSet),
									 fillArtworkDTO(resultSet));
		} catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_PROBLEM_FILLCOMMENTDTO_COMMENT, exception); 
        }

	}

	private final UserDTO fillCommentUserDTO(final ResultSet resultSet){

        try {

            return UserDTO.create(resultSet.getString("CommentUserId"), 
                                  resultSet.getString("CommentUserName"), 
                                  resultSet.getString("CommentUserLastName"),
                                  resultSet.getString("CommentUserMail"),
                                  resultSet.getString("CommentUserNickname"),
                                  resultSet.getString("CommentUserPassword"),
                                  resultSet.getDate("CommentUserBitrh"),
                                  resultSet.getString("CommentUserDescription"),
                                  resultSet.getBoolean("CommentUserIsPrivate"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_PROBLEM_FILLCOMMENTUSERDTO_COMMENT, exception); 
        }

    }

    private final ArtworkDTO fillArtworkDTO(final ResultSet resultSet){

        try {

            return ArtworkDTO.create(resultSet.getString("CommentArtworkID"), 
                                     resultSet.getString("ArtworkTittle"), 
                                     resultSet.getString("ArtworkDescription"),
                                     resultSet.getDate("ArtworkPublicationDate"),
                                     fillFileDTO(resultSet),
                                     fillArtworkTypeDTO(resultSet),
                                     fillUserDTO(resultSet));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_PROBLEM_FILLARTWORKDTO_COMMENT, exception); 
        }

    }

	private final FileDTO fillFileDTO(final ResultSet resultSet){

        try {

            return FileDTO.create(resultSet.getString("FileId"), 
                                  resultSet.getString("FilePathFile"), 
                                  fillFileTypeDTO(resultSet));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_PROBLEM_FILLFILEDTO_COMMENT, exception); //TODO
        }

    }

	private final FileTypeDTO fillFileTypeDTO(final ResultSet resultSet){

        try {

            return FileTypeDTO.create(resultSet.getString("FileTypeFileId"), 
                                  		 resultSet.getString("FileTypeName"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_PROBLEM_FILLFILETYPEDTO_COMMENT, exception); 
        }

    }

	private final ArtworkTypeDTO fillArtworkTypeDTO(final ResultSet resultSet){

        try {

            return ArtworkTypeDTO.create(resultSet.getString("ArtworkTypeId"), 
                                  		 resultSet.getString("ArtworkTypeName"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_PROBLEM_FILLARTWORKTYPEDTO_COMMENT, exception); 
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
                                  resultSet.getDate("UserBitrh"),
                                  resultSet.getString("UserDescription"),
                                  resultSet.getBoolean("UserIsPrivate"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_PROBLEM_FILLUSERDTO_COMMENT, exception); 
        }

    }

    private final List<CommentDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters) {

        try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())) {
            
            setParametersValues(preparedStatement, parameters);

            return executeQuery(preparedStatement);

        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_COMMENT, exception);
        }

    }


    private void setParametersValues(PreparedStatement preparedStatement, List<Object> parameters) {
        try {
            for(int index = 0; index < parameters.size(); index++){
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_PROBLEM_SETPARAMETERSVALUES_COMMENT, exception); 
        } catch(final Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_SETPARAMETERSVALUES_COMMENT, exception); 
        }
    }

    private final List<CommentDTO> executeQuery(PreparedStatement preparedStatement){

        try (final var resultSet = preparedStatement.executeQuery()) {
            return fillResults(resultSet);
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_PROBLEM_EXECUTEQUERY_COMMENT, exception); 
        } catch(final DataCustomException exception) {
            throw exception;
        } catch(final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.CommentPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECUTEQUERY_COMMENT, exception); 
        }
    }

}
