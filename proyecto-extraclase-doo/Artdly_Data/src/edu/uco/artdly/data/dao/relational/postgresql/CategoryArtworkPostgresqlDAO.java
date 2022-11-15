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
		
		final var sql = "INSERT INTO categoryartwork(id, artwork_id, category_id) VALUES (?, ?, ?)";
		
		try (final var prepareStatement = getConnection().prepareStatement(sql)) {
			
			prepareStatement.setString(1, categoryArtwork.getIdAsString());
			prepareStatement.setString(2, categoryArtwork.getArtwork().getIdAsString());
			prepareStatement.setString(3, categoryArtwork.getCategory().getIdAsString());
			prepareStatement.executeUpdate();

		} catch (SQLException exception) {
			throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_PROBLEM_CREATE_CATEGORYARTWORK, exception); 
		} catch (Exception exception) {
			throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_CATEGORYARTWORK, exception); 
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

		final var sql = "UPDATE categoryartwork SET id = ?, artwork_id = ?, category_id = ? ";
		
		try (final var prepareStatement = getConnection().prepareStatement(sql)) {
			
			prepareStatement.setString(1, categoryArtwork.getIdAsString());
			prepareStatement.setString(2, categoryArtwork.getArtwork().getIdAsString());
			prepareStatement.setString(3, categoryArtwork.getCategory().getIdAsString());
			prepareStatement.executeUpdate();

		} catch (SQLException exception) {
			throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_PROBLEM_UPDATE_CATEGORYARTWORK, exception); 
		} catch (Exception exception) {
			throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_UPDATE_CATEGORYARTWORK, exception); 
		}
		
	}

	@Override
	public void delete(UUID id) {
		
		final var sql = "DELETE FROM categoryartwork WHERE id = ?";

		try (final var prepareStatement = getConnection().prepareStatement(sql)) {
            
            prepareStatement.setString(1, getUUIDAsString(id));
			prepareStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_PROBLEM_DELETE_CATEGORYARTWORK, exception); 
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_DELETE_CATEGORYARTWORK, exception); 
        }
		
	}

	private final void createSelectFrom(final StringBuilder sqlBuilder){
		sqlBuilder.append("SELECT car.id AS CategoryArtworkId, ");
		sqlBuilder.append("       car.artwork_id AS CategoryArtworkIdArtwork, ");
		sqlBuilder.append("       art.tittle AS ArtworkTittle, ");
		sqlBuilder.append("       art.description AS ArtworkDescription, ");
		sqlBuilder.append("       art.publication_date AS ArtworkPublicationDate, ");
		sqlBuilder.append("       art.file_id AS FileId, ");
		sqlBuilder.append("       fil.path_file AS FilePathFile, ");
		sqlBuilder.append("       fil.type_file_id AS FileTypeFileId, ");
		sqlBuilder.append("       fit.file_type AS FileTypeName, ");
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
		sqlBuilder.append("       car.category_id AS CategoryArtworkIdCategory, ");
		sqlBuilder.append("       cat.name AS CategoryName, ");
		sqlBuilder.append("       cat.description AS CategoryDescription ");
		sqlBuilder.append("FROM categoryartwork car ");
		sqlBuilder.append("JOIN artwork art ON car.artwork_id = art.id ");
		sqlBuilder.append("JOIN file fil ON art.file_id = fil.id ");
		sqlBuilder.append("JOIN filetype fit ON fil.type_file_id = fit.id ");
		sqlBuilder.append("JOIN artworktype aty ON art.artwork_type_id = aty.id ");
		sqlBuilder.append("JOIN public.user usr ON art.user_id = usr.id ");
		sqlBuilder.append("JOIN category cat ON car.category_id = cat.id ");
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
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("artwork_id = ? ");
				setWhere = false;
				parameters.add(categoryArtwork.getArtwork().getIdAsString());
			}
			if(!ObjectHelper.isNull(categoryArtwork.getCategory().getIdAsString())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("category_id = ? ");
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
            throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_PROBLEM_FILLRESULTS_CATEGORYARTWORK, exception); 
        } catch (final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_FILLRESULTS_CATEGORYARTWORK, exception); 
        }

    }

	private final CategoryArtworkDTO fillCategoryArtworkDTO(final ResultSet resultSet){

		try {
			return CategoryArtworkDTO.create(resultSet.getString("CategoryArtworkId"), 
										     fillArtworkDTO(resultSet), 
											 fillCategoryDTO(resultSet));
		} catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_PROBLEM_FILLCATEGORYARTWORKDTO_CATEGORYARTWORK, exception); 
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
            throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_PROBLEM_FILLARTWORKDTO_CATEGORYARTWORK, exception); 
        }

    }

	private final FileDTO fillFileDTO(final ResultSet resultSet){

        try {

            return FileDTO.create(resultSet.getString("FileId"), 
                                  resultSet.getString("FilePathFile"), 
                                  fillFileTypeDTO(resultSet));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_PROBLEM_FILLFILEDTO_CATEGORYARTWORK, exception);
        }

    }

	private final FileTypeDTO fillFileTypeDTO(final ResultSet resultSet){

        try {

            return FileTypeDTO.create(resultSet.getString("FileTypeFileId"), 
                                  		 resultSet.getString("FileTypeName"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_PROBLEM_FILLFILETYPEDTO_CATEGORYARTWORK, exception);
        }

    }

	private final ArtworkTypeDTO fillArtworkTypeDTO(final ResultSet resultSet){

        try {

            return ArtworkTypeDTO.create(resultSet.getString("ArtworkTypeId"), 
                                  		 resultSet.getString("ArtworkTypeName"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_PROBLEM_FILLARTWORKTYPEDTO_CATEGORYARTWORK, exception); 
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
            throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_PROBLEM_FILLUSERDTO_CATEGORYARTWORK, exception); 
        }

    }
    
    
	private final CategoryDTO fillCategoryDTO(final ResultSet resultSet){

        try {

            return CategoryDTO.create(resultSet.getString("CategoryArtworkIdCategory"), 
                                  	  resultSet.getString("CategoryName"), 
                                  	  resultSet.getString("CategoryDescription"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_PROBLEM_FILLCATEGORYDTO_CATEGORYARTWORK, exception); 
        }

    }

    private final List<CategoryArtworkDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters) {

        try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())) {
            
            setParametersValues(preparedStatement, parameters);

            return executeQuery(preparedStatement);

        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_PROBLEM_PREPAREANDEXECUTEQUERY_CATEGORYARTWORK, exception);
        }

    }


    private void setParametersValues(PreparedStatement preparedStatement, List<Object> parameters) {
        try {
            for(int index = 0; index < parameters.size(); index++){
                preparedStatement.setObject(index + 1, parameters.get(index));
            }
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_PROBLEM_SETPARAMETERSVALUES_CATEGORYARTWORK, exception); 
        } catch(final Exception exception) {
            throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_SETPARAMETERSVALUES_CATEGORYARTWORK, exception); 
        }
    }

    private final List<CategoryArtworkDTO> executeQuery(PreparedStatement preparedStatement){

        try (final var resultSet = preparedStatement.executeQuery()) {
            return fillResults(resultSet);
        } catch(final SQLException exception){
            throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_PROBLEM_EXECUTEQUERY_CATEGORYARTWORK, exception); 
        } catch(final DataCustomException exception) {
            throw exception;
        } catch(final Exception exception){
            throw DataCustomException.CreateTechnicalException(Messages.CategoryArtworkPostgresqlDAO.TECHNICAL_UNEXPECTED_PROBLEM_EXECUTEQUERY_CATEGORYARTWORK, exception); 
        }
    }

}
