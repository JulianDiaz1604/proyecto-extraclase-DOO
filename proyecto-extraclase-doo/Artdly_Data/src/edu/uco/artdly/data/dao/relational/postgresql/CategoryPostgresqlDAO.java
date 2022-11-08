package edu.uco.artdly.data.dao.relational.postgresql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.uco.artdly.crosscutting.exception.data.DataCustomException;
import edu.uco.artdly.crosscutting.helper.ObjectHelper;
import edu.uco.artdly.crosscutting.helper.UUIDHelper;
import edu.uco.artdly.data.dao.CategoryDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.CategoryDTO;

public class CategoryPostgresqlDAO  extends DAORelational implements CategoryDAO {

	public CategoryPostgresqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(CategoryDTO category) {
		
		final var sql ="INSERT INTO category (id, name, description) VALUES (?, ?. ?)";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			
			preparedStatement.setString (1, category.getIdAsString());
			preparedStatement.setString (2, category.getName());
			preparedStatement.setString (3, category.getDescription());
            preparedStatement.executeUpdate();
            
		} catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException("");  //TODO: create message
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(null, exception);  //TODO create message
        }
		
	}


	@Override
	public List<CategoryDTO> find(CategoryDTO category) {

        var parameters = new ArrayList<Object>();
        final var sqlBuilder = new StringBuilder();

        createSelectFrom(sqlBuilder);
        createWhere(sqlBuilder, category, parameters);
        createOrderBy(sqlBuilder);

        return prepareAndExecuteQuery(sqlBuilder, parameters);

	}

	@Override
	public void update(CategoryDTO category) {
		final var sql= "UPDATE category SET id = ?, name = ?, description = ?";
		
		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, category.getIdAsString());
			preparedStatement.setString(2, category.getName());
			preparedStatement.setString(3, category.getDescription());
			
		} catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(null, exception);  //TODO create message
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(null, exception);  //TODO create message
        }
		
	}
	
	private final void createSelectFrom(final StringBuilder sqlBuilder) {
		sqlBuilder.append("SELECT cat.id AS CategoryId, ");
		sqlBuilder.append("       cat.name AS CategoryName, ");
		sqlBuilder.append("       cat.description AS CategoryDescription ");
		sqlBuilder.append("FROM category cat ");
	}


	private final void createWhere(final StringBuilder sqlBuilder, final CategoryDTO category, final List<Object> parameters){
	
	    var setWhere = true;
	
	    if(!ObjectHelper.isNull(category)){
	
	        if (!UUIDHelper.isDefaultUUID(category.getId())){
	            sqlBuilder.append("WHERE id = ? ");
	            setWhere = false;
	            parameters.add(category.getIdAsString());
	        }
	        if (!ObjectHelper.isNull(category.getName())){
	            sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("name = ? ");
	            setWhere = false;
	            parameters.add(category.getName());
	        }
	        if (!ObjectHelper.isNull(category.getDescription())){
	            sqlBuilder.append(setWhere ? "WHERE " : "AND ").append("description = ? ");
	            setWhere = false;
	            parameters.add(category.getDescription());
	        }
	    }
	}


	private final void createOrderBy(final StringBuilder sqlBuilder){
	
		sqlBuilder.append("ORDER BY cat.id");
	
	}



	private final List<CategoryDTO> executeQuery(PreparedStatement preparedStatement){
	
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



	private final List<CategoryDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters) {
	
	    try (final var preparedStatement = getConnection().prepareStatement(sqlBuilder.toString())) {
	        
	        setParametersValues(preparedStatement, parameters);
	
	        return executeQuery(preparedStatement);
	
	    } catch (Exception exception) {
	        throw DataCustomException.CreateTechnicalException(null, exception); // TODO create message
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


    private final List<CategoryDTO> fillResults(final ResultSet resultSet){

        try{

            var results = new ArrayList<CategoryDTO>();

            while(resultSet.next()){

                results.add(fillCategoryDTO(resultSet));

            }

            return results;

        } catch (final SQLException exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        } catch (final Exception exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        }

    }
    
    
	private final CategoryDTO fillCategoryDTO(final ResultSet resultSet){

        try {

            return CategoryDTO.create(resultSet.getString("CategoryId"), 
                                  	  resultSet.getString("CategoryName"), 
                                  	  resultSet.getString("CategoryDescription"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO
        }

    }
	
}