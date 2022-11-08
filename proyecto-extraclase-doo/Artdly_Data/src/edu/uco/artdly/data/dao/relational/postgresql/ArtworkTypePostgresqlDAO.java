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
import edu.uco.artdly.data.dao.ArtworkTypeDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;

public class ArtworkTypePostgresqlDAO extends DAORelational implements ArtworkTypeDAO {

	public ArtworkTypePostgresqlDAO(Connection connection) {
		super(connection);
	}

	@Override
	public void create(ArtworkTypeDTO artworkType) {
		final var sql = "INSERT INTO artworktype(id, name) VALUES (?, ?)";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
			
			preparedStatement.setString(1, artworkType.getIdAsString());
			preparedStatement.setString(2, artworkType.getName());
			preparedStatement.executeUpdate();

		} catch (SQLException exception) {
			throw DataCustomException.CreateTechnicalException(null, exception); //TODO: create message for exception
		} catch (Exception exception) {
			throw DataCustomException.CreateTechnicalException(null, exception); //TODO: create message for exception
		}
	}

	@Override
	public List<ArtworkTypeDTO> find(ArtworkTypeDTO artworkType) {
		
		var parameters = new ArrayList<Object>();
		final var sqlBuilder = new StringBuilder();

		createSelectFrom(sqlBuilder);
		createWhere(sqlBuilder, artworkType, parameters);
		createOrderBy(sqlBuilder);

		return prepareAndExecuteQuery(sqlBuilder, parameters);

	}

	@Override
	public void update(ArtworkTypeDTO artworkType) {
		final var sql = "UPDATE artworktype set id = ?, name = ? ";

		try(final var prepareStatement = getConnection().prepareStatement(sql)) {
			
			prepareStatement.setString(1, artworkType.getIdAsString());
			prepareStatement.setString(2, artworkType.getName());
			prepareStatement.executeUpdate();

		} catch (SQLException exception) {
			throw DataCustomException.CreateTechnicalException(null, exception); //TODO: create message for exception
		} catch (Exception exception) {
			throw DataCustomException.CreateTechnicalException(null, exception); //TODO: create message for exception
		}
		
	}

	@Override
	public void delete(UUID id) {
		final var sql = "DELETE FROM artworktype WHERE id = ?";

		try (final var preparedStatement = getConnection().prepareStatement(sql)) {
            
            preparedStatement.setString(1, getUUIDAsString(id));
			preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO: create message for exception
        } catch (Exception exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO: create message for exception
        }
		
	}

	private final void createSelectFrom(final StringBuilder sqlBuilder){
		sqlBuilder.append("SELECT atp.id AS ArtworkTypeId, ");
        sqlBuilder.append("       atp.name AS ArtworkTypeName ");
		sqlBuilder.append("FROM artworktype atp ");
	}

	private final void createWhere(final StringBuilder sqBuilder, final ArtworkTypeDTO artworktype, final List<Object> parameters){
		
		var setWhere = true;

		if(!ObjectHelper.isNull(artworktype)){

			if(!UUIDHelper.isDefaultUUID(artworktype.getId())){
				sqBuilder.append("WHERE id = ? ");
				setWhere = false;
				parameters.add(artworktype.getIdAsString());
			}
			if(!ObjectHelper.isNull(artworktype.getName())){
				sqBuilder.append(setWhere ? "WHERE " : "AND ").append("name = ? ");
				parameters.add(artworktype.getName());
			}

		}
	}

	private final void createOrderBy(final StringBuilder sqlBuilder){

		sqlBuilder.append("ORDER BY atp.id");

	}

    private final List<ArtworkTypeDTO> fillResults(final ResultSet resultSet){

        try{

            var results = new ArrayList<ArtworkTypeDTO>();

            while(resultSet.next()){

                results.add(fillArtworkTypeDTO(resultSet));

            }

            return results;

        } catch (final SQLException exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO: create message
        } catch (final Exception exception){
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO: create message
        }

    }

	private final ArtworkTypeDTO fillArtworkTypeDTO(final ResultSet resultSet){

        try {

            return ArtworkTypeDTO.create(resultSet.getString("ArtworkTypeId"), 
                                  		 resultSet.getString("ArtworkTypeName"));
            
        } catch (SQLException exception) {
            throw DataCustomException.CreateTechnicalException(null, exception); //TODO: create message
        }

    }

    private final List<ArtworkTypeDTO> prepareAndExecuteQuery(final StringBuilder sqlBuilder, final List<Object> parameters) {

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

    private final List<ArtworkTypeDTO> executeQuery(PreparedStatement preparedStatement){

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
