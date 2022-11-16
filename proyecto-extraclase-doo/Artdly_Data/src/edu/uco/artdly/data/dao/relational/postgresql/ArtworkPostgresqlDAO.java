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
import edu.uco.artdly.data.dao.ArtworkDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.domain.FileDTO;
import edu.uco.artdly.domain.FileTypeDTO;
import edu.uco.artdly.domain.UserDTO;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class ArtworkPostgresqlDAO  extends DAORelational implements ArtworkDAO {

	public ArtworkPostgresqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(ArtworkDTO artwork) {

		final var sql = "INSERT INTO artwork(id, tittle, description, publication_date, file_id, artwork_type_id, user_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			
			preparedStatement.setString(1, artwork.getIdAsString());
			preparedStatement.setString(2, artwork.getTittle());
			preparedStatement.setString(3, artwork.getDescription());
			preparedStatement.setDate(4, artwork.getPublicationDate());
			preparedStatement.setString(5, artwork.getFile().getIdAsString());
			preparedStatement.setString(6, artwork.getArtworkType().getIdAsString());
			preparedStatement.setString(7, artwork.getUser().getIdAsString());
			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_PROBLEM_CREATE_ARTWORK, exception);
		} catch (Exception exception) {
			throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_ARTWORK, exception); 
		}

	}
    @Override
    public List<ArtworkDTO> findAll() {
        var parameters = new ArrayList<Object>();
        final var sqlBuilder = new StringBuilder();

        createSelectFrom(sqlBuilder);
        createOrderBy(sqlBuilder);

        return prepareAndExecuteQuery(sqlBuilder, parameters);
    }

	@Override
	public List<ArtworkDTO> find(ArtworkDTO artwork) {
		
		var parameters = new ArrayList<Object>();
		final var sqlBuilder = new StringBuilder();

		createSelectFrom(sqlBuilder);
		createWhere(sqlBuilder, artwork, parameters);
		createOrderBy(sqlBuilder);

		return prepareAndExecuteQuery(sqlBuilder, parameters);
	}

	@Override
	public void update(ArtworkDTO artwork) {

		final var sql = "UPDATE artwork SET id = ?, tittle = ?, description = ?, publication_date = ?, file_id = ?, artwork_type_id = ?, user_id = ? ";
		
		try (final var prepareStatement = getConnection().prepareStatement(sql)) {
			
			prepareStatement.setString(1, artwork.getIdAsString());
			prepareStatement.setString(2, artwork.getTittle());
			prepareStatement.setString(3, artwork.getDescription());
			prepareStatement.setString(4, artwork.getPublicationDate().toString());
			prepareStatement.setString(5, artwork.getFile().getIdAsString());
			prepareStatement.setString(6, artwork.getArtworkType().getIdAsString());
			prepareStatement.setString(7, artwork.getUser().getIdAsString());
			prepareStatement.executeUpdate();

		} catch (SQLException exception) {
			throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_PROBLEM_UPDATE_ARTWORK, exception); 
		} catch (Exception exception) {
			throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_ARTWORK, exception); 
		}
		
	}

	@Override
	public void delete(UUID id) {
		
		final var sql = "DELETE FROM artwork WHERE id = ?";

		try (final var prepareStatement = getConnection().prepareStatement(sql)) {
            
            prepareStatement.setString(1, getUUIDAsString(id));
			prepareStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_PROBLEM_DELETE_ARTWORK, exception); 
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_ARTWORK, exception);
        }
		
	}

	private final void createSelectFrom(final StringBuilder sqlBuilder){
		sqlBuilder.append("SELECT art.id AS ArtworkId, ");
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
		sqlBuilder.append("       usr.name AS UserName, ");
        sqlBuilder.append("       usr.last_name AS UserLastName, ");
        sqlBuilder.append("       usr.mail AS UserMail, ");
        sqlBuilder.append("       usr.username AS UserNickname, ");
        sqlBuilder.append("       usr.password AS UserPassword, ");
        sqlBuilder.append("       usr.birth_date AS UserBirthdate, ");
        sqlBuilder.append("       usr.description AS UserDescription, ");
        sqlBuilder.append("       usr.is_private AS UserIsPrivate ");
		sqlBuilder.append("FROM artwork art ");
		sqlBuilder.append("JOIN file fil ON art.file_id = fil.id ");
		sqlBuilder.append("JOIN filetype fit ON fil.type_file_id = fit.id ");
		sqlBuilder.append("JOIN artworktype aty ON art.artwork_type_id = aty.id ");
		sqlBuilder.append("JOIN public.user usr ON art.user_id = usr.id ");
	}

	private final void createWhere(final StringBuilder sqBuilder, final ArtworkDTO artwork, final List<Object> parameters){
		
		var setWhere = true;

		if(!ObjectHelper.isNull(artwork)){

			if(!UUIDHelper.isDefaultUUID(artwork.getId())){
				sqBuilder.append("WHERE id = ? ");
				setWhere = false;
				parameters.add(artwork.getIdAsString());
			}
			if(!ObjectHelper.isNull(artwork.getTittle())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("tittle = ? ");
				setWhere = false;
				parameters.add(artwork.getTittle());
			}
			if(!ObjectHelper.isNull(artwork.getDescription())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("description = ? ");
				setWhere = false;
				parameters.add(artwork.getTittle());
			}
			if(!ObjectHelper.isNull(artwork.getPublicationDate())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("publication_date = ? ");
				setWhere = false;
				parameters.add(artwork.getPublicationDate());
			}
			if(!ObjectHelper.isNull(artwork.getFile())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("file_id = ? ");
				setWhere = false;
				parameters.add(artwork.getFile());
			}
			if(!ObjectHelper.isNull(artwork.getArtworkType().getId())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("artwork_type_id = ? ");
				setWhere = false;
				parameters.add(artwork.getArtworkType().getIdAsString());
			}
			if(!ObjectHelper.isNull(artwork.getUser().getId())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("user_id = ? ");
				parameters.add(artwork.getUser().getIdAsString());
			}

		}
	}

	private final void createOrderBy(final StringBuilder sqlBuilder){

		sqlBuilder.append("ORDER BY art.publication_date DESC");

	}

    private final List<ArtworkDTO> fillResults(final ResultSet resultSet){

        try{

            var results = new ArrayList<ArtworkDTO>();

            while(resultSet.next()){

                results.add(fillArtworkDTO(resultSet));

            }

            return results;

        } catch (final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_PROBLEM_FILLRESULTS_ARTWORK, exception); 
        } catch (final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILLRESULTS_ARTWORK, exception); 

    }
    }

    private final ArtworkDTO fillArtworkDTO(final ResultSet resultSet){

        try {

            return ArtworkDTO.create(resultSet.getString("ArtworkID"), 
                                     resultSet.getString("ArtworkTittle"), 
                                     resultSet.getString("ArtworkDescription"),
                                     resultSet.getDate("ArtworkPublicationDate"),
                                     fillFileDTO(resultSet),
                                     fillArtworkTypeDTO(resultSet),
                                     fillUserDTO(resultSet));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_PROBLEM_FILLARTWORKDTO_ARTWORK, exception); 
        }

    }

	private final FileDTO fillFileDTO(final ResultSet resultSet){

        try {

            return FileDTO.create(resultSet.getString("FileId"), 
                                  resultSet.getString("FilePathFile"), 
                                  fillFileTypeDTO(resultSet));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_PROBLEM_FILLFILEDTO_ARTWORK, exception); 
        }

    }

	private final FileTypeDTO fillFileTypeDTO(final ResultSet resultSet){

        try {

            return FileTypeDTO.create(resultSet.getString("FileTypeFileId"), 
                                  		 resultSet.getString("FileTypeName"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_PROBLEM_FILLFILETYPEDTO_ARTWORK, exception); 
        }

    }

	private final ArtworkTypeDTO fillArtworkTypeDTO(final ResultSet resultSet){

        try {

            return ArtworkTypeDTO.create(resultSet.getString("ArtworkTypeId"), 
                                  		 resultSet.getString("ArtworkTypeName"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_PROBLEM_FILLARTWORKTYPEDTO_ARTWORK, exception); 
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
            throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_PROBLEM_FILLUSERDTO_ARTWORK, exception); 
        }

    }

    private final List<ArtworkDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters) {

        try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())) {
            
            setParametersValues(preparedStatement, parameters);

            return executeQuery(preparedStatement);

        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_ARTWORK, exception);
        }

    }


    private void setParametersValues(PreparedStatement preparedStatement, List<Object> parameters) {
        try {
            for(int index = 0; index < parameters.size(); index++){
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_PROBLEM_SETPARAMETERSVALUES_ARTWORK, exception); 
        } catch(final Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_SETPARAMETERSVALUES_ARTWORK, exception); 
        }
    }

    private final List<ArtworkDTO> executeQuery(PreparedStatement preparedStatement){

        try (final var resultSet = preparedStatement.executeQuery()) {
            return fillResults(resultSet);
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_PROBLEM_EXECUTEQUERY_ARTWORK, exception); 
        } catch(final DataCustomException exception) {
            throw exception;
        } catch(final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.ArtworkPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECUTEQUERY_ARTWORK, exception); 
        }
    }



}
