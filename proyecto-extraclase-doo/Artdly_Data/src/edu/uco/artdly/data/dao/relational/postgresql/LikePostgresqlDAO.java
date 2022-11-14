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
import edu.uco.artdly.crosscutting.helper.ObjectHelper;
import edu.uco.artdly.crosscutting.helper.UUIDHelper;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.dao.LikeDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.domain.FileDTO;
import edu.uco.artdly.domain.FileTypeDTO;
import edu.uco.artdly.domain.LikeDTO;
import edu.uco.artdly.domain.UserDTO;

import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class LikePostgresqlDAO extends DAORelational implements LikeDAO {

	public LikePostgresqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(LikeDTO like) {

		final var sql = "INSERT INTO public.like (id, realization_date, user_id, artwork_id) VALUES (?, ?, ?, ?)";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			
			preparedStatement.setString(1, like.getIdAsString());
			preparedStatement.setDate(2, like.getRealizationDate());
			preparedStatement.setString(3, like.getUser().getIdAsString());
			preparedStatement.setString(4, like.getArtwork().getIdAsString());
			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_PROBLEM_CREATE_LIKE, exception); 
		} catch (Exception exception) {
			throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_LIKE, exception); 
		}
		
	}

	@Override
	public List<LikeDTO> find(LikeDTO like) {
		
		var parameters = new ArrayList<Object>();
		final var sqlBuilder = new StringBuilder();

		createSelectFrom(sqlBuilder);
		createWhere(sqlBuilder, like, parameters);
		createOrderBy(sqlBuilder);

		return prepareAndExecuteQuery(sqlBuilder, parameters);
		
	}


	@Override
	public void delete(UUID id) {
		
		final var sql = "DELETE FROM public.like lik WHERE lik.id = ?";

		try (final var prepareStatement = getConnection().prepareStatement(sql)) {
            
            prepareStatement.setString(1, getUUIDAsString(id));
			prepareStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_PROBLEM_DELETE_LIKE, exception); 
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_LIKE, exception); 
        }
		
	}

	private final void createSelectFrom(final StringBuilder sqlBuilder){
		sqlBuilder.append("SELECT lik.id AS LikeId, ");
		sqlBuilder.append("       lik.realization_date AS LikeRealizationDate, ");
		sqlBuilder.append("       lik.user_id AS LikeUserId, ");
		sqlBuilder.append("       usr1.name AS LikeUserName, ");
        sqlBuilder.append("       usr1.last_name AS LikeUserLastName, ");
        sqlBuilder.append("       usr1.mail AS LikeUserMail, ");
        sqlBuilder.append("       usr1.username AS LikeUserNickname, ");
        sqlBuilder.append("       usr1.password AS LikeUserPassword, ");
        sqlBuilder.append("       usr1.birth_date AS LikeUserBirthdate, ");
        sqlBuilder.append("       usr1.description AS LikeUserDescription, ");
        sqlBuilder.append("       usr1.is_private AS LikeUserIsPrivate, ");
		sqlBuilder.append("       lik.artwork_id AS LikeArtworkId, ");
		sqlBuilder.append("       art.tittle AS ArtworkTittle, ");
		sqlBuilder.append("       art.description AS ArtworkDescription, ");
		sqlBuilder.append("       art.publication_date AS ArtworkPublicationDate, ");
		sqlBuilder.append("       art.file_id AS FileId, ");
		sqlBuilder.append("       fil.path_file AS FilePathFile, ");
		sqlBuilder.append("       fil.type_file_id AS FileTypeFileId, ");
		sqlBuilder.append("       fit.name AS FileTypeName, ");
		sqlBuilder.append("       art.artwork_type_id AS ArtworkTypeId, ");
		sqlBuilder.append("       aty.name AS ArtworkTypeName, ");
		sqlBuilder.append("       art.user_id AS ArtworkUserId, ");
		sqlBuilder.append("       usr2.name AS UserName, ");
        sqlBuilder.append("       usr2.last_name AS UserLastName, ");
        sqlBuilder.append("       usr2.mail AS UserMail, ");
        sqlBuilder.append("       usr2.username AS UserNickname, ");
        sqlBuilder.append("       usr2.password AS UserPassword, ");
        sqlBuilder.append("       usr2.birth_date AS UserBirthdate, ");
        sqlBuilder.append("       usr2.description AS UserDescription, ");
        sqlBuilder.append("       usr2.is_private AS UserIsPrivate ");
		sqlBuilder.append("FROM public.like lik ");
		sqlBuilder.append("JOIN public.user usr1 ON lik.user_id = usr1.id ");
		sqlBuilder.append("JOIN artwork art ON lik.artwork_id = art.id ");
		sqlBuilder.append("JOIN file fil ON art.file_id = fil.id ");
		sqlBuilder.append("JOIN filetype fit ON fil.type_file_id = fit.id ");
		sqlBuilder.append("JOIN artworktype aty ON art.artwork_type_id = aty.id ");
		sqlBuilder.append("JOIN public.user usr2 ON art.user_id = usr2.id ");
	}

	private final void createWhere(final StringBuilder sqBuilder, final LikeDTO like, final List<Object> parameters){
		
		var setWhere = true;

		if(!ObjectHelper.isNull(like)){

			if(!UUIDHelper.isDefaultUUID(like.getId())){
				sqBuilder.append("WHERE lik.id = ? ");
				setWhere = false;
				parameters.add(like.getIdAsString());
			}
			if(!DateHelper.isDefaultDate(like.getRealizationDate())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("realization_date = ? ");
				setWhere = false;
				parameters.add(like.getRealizationDate());
			}
			if(!UUIDHelper.isDefaultUUID(like.getUser().getId())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("lik.user_id = ? ");
				setWhere = false;
				parameters.add(like.getUser().getIdAsString());
			}
			if(!UUIDHelper.isDefaultUUID(like.getArtwork().getId())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("artwork_id = ? ");
				parameters.add(like.getArtwork().getIdAsString());
			}

		}
	}

	private final void createOrderBy(final StringBuilder sqlBuilder){

		sqlBuilder.append("ORDER BY lik.id");

	}

    private final List<LikeDTO> fillResults(final ResultSet resultSet){

        try{

            var results = new ArrayList<LikeDTO>();

            while(resultSet.next()){

                results.add(fillLikeDTO(resultSet));

            }

            return results;

        } catch (final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_PROBLEM_FILLRESULTS_LIKE, exception); 
        } catch (final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILLRESULTS_LIKE, exception); 
        }

    }

	private final LikeDTO fillLikeDTO(final ResultSet resultSet){

		try {
			return LikeDTO.create(resultSet.getString("LikeId"), 
								  resultSet.getDate("LikeRealizationDate"), 
								  fillLikeUserDTO(resultSet),
								  fillArtworkDTO(resultSet));
		} catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_PROBLEM_FILLLIKEDTO_LIKE, exception); 
        }

	}

	private final UserDTO fillLikeUserDTO(final ResultSet resultSet){

        try {

            return UserDTO.create(resultSet.getString("LikeUserId"), 
                                  resultSet.getString("LikeUserName"), 
                                  resultSet.getString("LikeUserLastName"),
                                  resultSet.getString("LikeUserMail"),
                                  resultSet.getString("LikeUserNickname"),
                                  resultSet.getString("LikeUserPassword"),
                                  resultSet.getDate("LikeUserBirthdate"),
                                  resultSet.getString("LikeUserDescription"),
                                  resultSet.getBoolean("LikeUserIsPrivate"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_PROBLEM_FILLLIKEUSERDTO_LIKE, exception); 
        }

    }

    private final ArtworkDTO fillArtworkDTO(final ResultSet resultSet){

        try {

            return ArtworkDTO.create(resultSet.getString("LikeArtworkID"), 
                                     resultSet.getString("ArtworkTittle"), 
                                     resultSet.getString("ArtworkDescription"),
                                     resultSet.getDate("ArtworkPublicationDate"),
                                     fillFileDTO(resultSet),
                                     fillArtworkTypeDTO(resultSet),
                                     fillUserDTO(resultSet));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_PROBLEM_FILlARTWORKDTO_LIKE, exception); 
        }

    }

	private final FileDTO fillFileDTO(final ResultSet resultSet){

        try {

            return FileDTO.create(resultSet.getString("FileId"), 
                                  resultSet.getString("FilePathFile"), 
                                  fillFileTypeDTO(resultSet));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_PROBLEM_FILLFILEDTO_LIKE, exception); 
        }

    }

	private final FileTypeDTO fillFileTypeDTO(final ResultSet resultSet){

        try {

            return FileTypeDTO.create(resultSet.getString("FileTypeFileId"), 
                                  		 resultSet.getString("FileTypeName"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_PROBLEM_FILLFILETYPEDTO_LIKE, exception); 
        }

    }

	private final ArtworkTypeDTO fillArtworkTypeDTO(final ResultSet resultSet){

        try {

            return ArtworkTypeDTO.create(resultSet.getString("ArtworkTypeId"), 
                                  		 resultSet.getString("ArtworkTypeName"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_PROBLEM_FILLARTWORKTYPEDTO_lIKE, exception); 
        }

    }

	private final UserDTO fillUserDTO(final ResultSet resultSet){

        try {

            return UserDTO.create(resultSet.getString("ArtworkUserId"), 
                                  resultSet.getString("UserName"), 
                                  resultSet.getString("UserLastName"),
                                  resultSet.getString("UserMail"),
                                  resultSet.getString("UserNickname"),
                                  resultSet.getString("UserPassword"),
                                  resultSet.getDate("UserBirthdate"),
                                  resultSet.getString("UserDescription"),
                                  resultSet.getBoolean("UserIsPrivate"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_PROBLEM_FILLUSERDTO_lIKE, exception); 
        }

    }

    private final List<LikeDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters) {

        try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())) {
            
            setParametersValues(preparedStatement, parameters);

            return executeQuery(preparedStatement);

        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_lIKE, exception);
        }

    }


    private void setParametersValues(PreparedStatement preparedStatement, List<Object> parameters) {
        try {
            for(int index = 0; index < parameters.size(); index++){
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_PROBLEM_SETPARAMETERSVALUES_lIKE, exception); 
        } catch(final Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_SETPARAMETERSVALUES_lIKE, exception); 
        }
    }

    private final List<LikeDTO> executeQuery(PreparedStatement preparedStatement){

        try (final var resultSet = preparedStatement.executeQuery()) {
            return fillResults(resultSet);
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_PROBLEM_EXECUTEQUERY_lIKE, exception); 
        } catch(final DataCustomException exception) {
            throw exception;
        } catch(final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.LikePostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECUTEQUERY_lIKE, exception); 
        }
    }

}
