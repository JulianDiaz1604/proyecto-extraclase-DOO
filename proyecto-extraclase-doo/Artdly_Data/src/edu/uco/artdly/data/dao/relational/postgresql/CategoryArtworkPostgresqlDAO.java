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
import edu.uco.artdly.data.dao.CategoryArtworkDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.domain.CategoryArtworkDTO;
import edu.uco.artdly.domain.CategoryDTO;
import edu.uco.artdly.domain.FileDTO;
import edu.uco.artdly.domain.FileTypeDTO;
import edu.uco.artdly.domain.UserDTO;

import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class CategoryArtworkPostgresqlDAO extends DAORelational implements CategoryArtworkDAO {

	public CategoryArtworkPostgresqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(CategoryArtworkDTO categoryArtwork) {
		
		final var sql = "INSERT INTO categoryartwork(id, artworkId, categoryId) VALUES (?, ?, ?)";
		
		try (final var prepareStatement = getConnection().prepareStatement(sql)) {
			
			prepareStatement.setString(1, categoryArtwork.getIdAsString());
			prepareStatement.setString(2, categoryArtwork.getArtwork().getIdAsString());
			prepareStatement.setString(3, categoryArtwork.getCategory().getIdAsString());
			prepareStatement.executeUpdate();

		} catch (SQLException exception) {
			throw DataCustomException.CreateTechnicalException(null, exception); //TODO: create message for exception
		} catch (Exception exception) {
			throw DataCustomException.CreateTechnicalException(null, exception); //TODO: create message for exception
		}
		
	}

	@Override
	public List<CategoryArtworkDTO> find(CategoryArtworkDTO categoryArtwork) {
		
		var parameters = new ArrayList<Object>();
		final var sqlBuilder = new StringBuilder();

		createSelectFrom(sqlBuilder);
		createWhere(sqlBuilder, categoryArtwork, parameters);
		createOrderBy(sqlBuilder);

		return prepareAndExecuteQuery(sqlBuilder, parameters);

	}

	@Override
	public void update(CategoryArtworkDTO categoryArtwork) {

		final var sql = "UPDATE categoryartwork SET id = ?, artworkId = ?, categoryId = ? ";
		
		try (final var prepareStatement = getConnection().prepareStatement(sql)) {
			
			prepareStatement.setString(1, categoryArtwork.getIdAsString());
			prepareStatement.setString(2, categoryArtwork.getArtwork().getIdAsString());
			prepareStatement.setString(3, categoryArtwork.getCategory().getIdAsString());
			prepareStatement.executeUpdate();

		} catch (SQLException exception) {
			throw DataCustomException.CreateTechnicalException(null, exception); //TODO: create message for exception
		} catch (Exception exception) {
			throw DataCustomException.CreateTechnicalException(null, exception); //TODO: create message for exception
		}
		
	}

	@Override
	public void delete(UUID id) {
		
		final var sql = "DELETE FROM categoryartwork WHERE id = ?";

		try (final var prepareStatement = getConnection().prepareStatement(sql)) {
            
            prepareStatement.setString(1, getUUIDAsString(id));
			prepareStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        }
		
	}

	private final void createSelectFrom(final StringBuilder sqlBuilder){
		sqlBuilder.append("SELECT car.id AS CategoryArtworkId, ");
		sqlBuilder.append("       car.artworkId AS CategoryArtworkIdArtwork, ");
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
		sqlBuilder.append("       usr.name AS UserName, ");
        sqlBuilder.append("       usr.lastName AS UserLastName, ");
        sqlBuilder.append("       usr.mail AS UserMail, ");
        sqlBuilder.append("       usr.username AS UserNickname, ");
        sqlBuilder.append("       usr.password AS UserPassword, ");
        sqlBuilder.append("       usr.birthDate AS UserBirthdate, ");
        sqlBuilder.append("       usr.description AS UserDescription, ");
        sqlBuilder.append("       usr.isPrivate AS UserIsPrivate ");
		sqlBuilder.append("       car.categoryId AS CategoryArtworkIdCategory, ");
		sqlBuilder.append("       cat.name AS CategoryName, ");
		sqlBuilder.append("       cat.description AS CategoryDescription ");
		sqlBuilder.append("FROM categoryartwork car ");
		sqlBuilder.append("JOIN artwork art ON car.artworkId = art.id ");
		sqlBuilder.append("JOIN file fil ON art.fileId = fil.id ");
		sqlBuilder.append("JOIN filetype fit ON fil.typeFileId = fit.id ");
		sqlBuilder.append("JOIN artworktype aty ON art.artworkTypeId = aty.id ");
		sqlBuilder.append("JOIN user usr ON art.userId = usr.id ");
		sqlBuilder.append("JOIN category cat ON car.categoryId = cat.id ");
	}

	private final void createWhere(final StringBuilder sqBuilder, final CategoryArtworkDTO categoryArtwork, final List<Object> parameters){
		
		var setWhere = true;

		if(!ObjectHelper.isNull(categoryArtwork)){

			if(!UUIDHelper.isDefaultUUID(categoryArtwork.getId())){
				sqBuilder.append("WHERE id = ? ");
				setWhere = false;
				parameters.add(categoryArtwork.getIdAsString());
			}
			if(!ObjectHelper.isNull(categoryArtwork.getArtwork().getIdAsString())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("artworkId = ? ");
				setWhere = false;
				parameters.add(categoryArtwork.getArtwork().getIdAsString());
			}
			if(!ObjectHelper.isNull(categoryArtwork.getCategory().getIdAsString())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("categoryId = ? ");
				parameters.add(categoryArtwork.getCategory().getIdAsString());
			}

		}
	}

	private final void createOrderBy(final StringBuilder sqlBuilder){

		sqlBuilder.append("ORDER BY car.id");

	}

    private final List<CategoryArtworkDTO> fillResults(final ResultSet resultSet){

        try{

            var results = new ArrayList<CategoryArtworkDTO>();

            while(resultSet.next()){

                results.add(fillCategoryArtworkDTO(resultSet));

            }

            return results;

        } catch (final SQLException exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        } catch (final Exception exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        }

    }

	private final CategoryArtworkDTO fillCategoryArtworkDTO(final ResultSet resultSet){

		try {
			return CategoryArtworkDTO.create(resultSet.getString("CategoryArtworkId"), 
										     fillArtworkDTO(resultSet), 
											 fillCategoryDTO(resultSet));
		} catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        }

	}

    private final ArtworkDTO fillArtworkDTO(final ResultSet resultSet){

        try {

            return ArtworkDTO.create(resultSet.getString("CategoryArtworkIDArtwork"), 
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
                                  fillFileTypeDTO(resultSet));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        }

    }

	private final FileTypeDTO fillFileTypeDTO(final ResultSet resultSet){

        try {

            return FileTypeDTO.create(resultSet.getString("FileTypeFileId"), 
                                  		 resultSet.getString("FileTypeName"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO: create message
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
    
    
	private final CategoryDTO fillCategoryDTO(final ResultSet resultSet){

        try {

            return CategoryDTO.create(resultSet.getString("CategoryArtworkIdCategory"), 
                                  	  resultSet.getString("CategoryName"), 
                                  	  resultSet.getString("CategoryDescription"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        }

    }

    private final List<CategoryArtworkDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters) {

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

    private final List<CategoryArtworkDTO> executeQuery(PreparedStatement preparedStatement){

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
