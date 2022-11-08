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
import edu.uco.artdly.data.dao.ArtworkDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.domain.FileDTO;
import edu.uco.artdly.domain.UserDTO;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class ArtworkPostgresqlDAO  extends DAORelational implements ArtworkDAO {

	public ArtworkPostgresqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(ArtworkDTO artwork) {
		final var sql = "INSERT INTO ARTWORK(id, tittle, description, publicationDate, file, artworkType, user) VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			
			preparedStatement.setString(1, artwork.getIdAsString());
			preparedStatement.setString(2, artwork.getTittle());
			preparedStatement.setString(3, artwork.getDescription());
			preparedStatement.setString(4, artwork.getPublicationDate().toString());
			preparedStatement.setString(5, artwork.getFile().getIdAsString());
			preparedStatement.setString(6, artwork.getArtworkType().getIdAsString());
			preparedStatement.setString(7, artwork.getUser().getIdAsString());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
		} catch (Exception e) {
			//TODO
		}
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
		final var sql = "UPDATE artwork SET id = ?, tittle = ?, description = ?, publicationDate = ?, fileId = ?, artworkTypeId = ?, userId = ? ";
		
		try (final var prepareStatement = getConnection().prepareStatement(sql)) {
			
			prepareStatement.setString(1, artwork.getIdAsString());
			prepareStatement.setString(2, artwork.getTittle());
			prepareStatement.setString(3, artwork.getDescription());
			prepareStatement.setString(4, artwork.getPublicationDate().toString());
			prepareStatement.setString(5, artwork.getFile().getIdAsString());
			prepareStatement.setString(6, artwork.getArtworkType().getIdAsString());
			prepareStatement.setString(7, artwork.getUser().getIdAsString());
			prepareStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO: handle exception
		} catch (Exception e) {
			//TODO
		}
		
	}

	@Override
	public void delete(UUID id) {
		final var sql = "DELETE FROM artwork WHERE id = ?";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            
            preparedStatement.setString(1, getUUIDAsString(id));
			preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        }
		
	}

	private final void createSelectFrom(final StringBuilder sqlBuilder){
		sqlBuilder.append("SELECT art.id AS ArtworkId, ");
		sqlBuilder.append("       art.tittle AS ArtworkTittle, ");
		sqlBuilder.append("       art.description AS ArtworkDescription, ");
		sqlBuilder.append("       art.publicationDate AS ArtworkPublicationDate, ");
		sqlBuilder.append("       art.fileId AS FileId, ");
		sqlBuilder.append("       fil.pathFile AS FilePathFile, ");
		sqlBuilder.append("       fil.typeFile AS FileTypeFile, ");
		sqlBuilder.append("       art.artworkTypeId AS ArtworkTypeId, ");
		sqlBuilder.append("       aty.name AS ArtworkTypeName, ");
		sqlBuilder.append("       art.userId AS ArtworkUserId, ");
		sqlBuilder.append("       usr.name AS UserName, ");
        sqlBuilder.append("       usr.lastName AS UserLastName, ");
        sqlBuilder.append("       usr.mail AS UserMail, ");
        sqlBuilder.append("       usr.username AS UserNickname, ");
        sqlBuilder.append("       usr.password AS UserPassword, ");
        sqlBuilder.append("       usr.birthDate AS UserBirthdate, ");
        sqlBuilder.append("       usr.description AS UserDescription, ");
        sqlBuilder.append("       usr.isPrivate AS UserIsPrivate ");
		sqlBuilder.append("FROM artwork art ");
		sqlBuilder.append("JOIN file fil ON art.fileId = fil.id ");
		sqlBuilder.append("JOIN artworktype aty ON art.artworkTypeId = aty.id ");
		sqlBuilder.append("JOIN user usr ON art.userId = usr.id ");
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
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("publicationDate = ? ");
				setWhere = false;
				parameters.add(artwork.getPublicationDate());
			}
			if(!ObjectHelper.isNull(artwork.getFile())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("fileId = ? ");
				setWhere = false;
				parameters.add(artwork.getFile());
			}
			if(!ObjectHelper.isNull(artwork.getArtworkType().getId())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("artworkTypeId = ? ");
				setWhere = false;
				parameters.add(artwork.getArtworkType().getIdAsString());
			}
			if(!ObjectHelper.isNull(artwork.getUser().getId())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("userId = ? ");
				parameters.add(artwork.getUser().getIdAsString());
			}

		}
	}

	private final void createOrderBy(final StringBuilder sqlBuilder){

		sqlBuilder.append("ORDER BY art.id");

	}

    private final List<ArtworkDTO> fillResults(final ResultSet resultSet){

        try{

            var results = new ArrayList<ArtworkDTO>();

            while(resultSet.next()){

                results.add(fillArtworkDTO(resultSet));

            }

            return results;

        } catch (final SQLException exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        } catch (final Exception exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
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
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        }

    }

	private final FileDTO fillFileDTO(final ResultSet resultSet){

        try {

            return FileDTO.create(resultSet.getString("FileId"), 
                                  resultSet.getString("FilePathFile"), 
                                  resultSet.getString("FileTypeFile"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        }

    }

	private final ArtworkTypeDTO fillArtworkTypeDTO(final ResultSet resultSet){

        try {

            return ArtworkTypeDTO.create(resultSet.getString("ArtworkTypeId"), 
                                  		 resultSet.getString("ArtworkTypeName"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
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
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO: message
        }

    }

    private final List<ArtworkDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters) {

        try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())) {
            
            setParametersValues(preparedStatement, parameters);

            return executeQuery(preparedStatement);

        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(null, exception);
        }

    }


    private void setParametersValues(PreparedStatement preparedStatement, List<Object> parameters) {
        try {
            for(int index = 0; index < parameters.size(); index++){
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO crear excepcion
        } catch(final Exception exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO crear excepcion
        }
    }

    private final List<ArtworkDTO> executeQuery(PreparedStatement preparedStatement){

        try (final var resultSet = preparedStatement.executeQuery()) {
            return fillResults(resultSet);
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO crear excepcion
        } catch(final DataCustomException exception) {
            throw exception;
        } catch(final Exception exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO crear excepcion
        }
    }

}