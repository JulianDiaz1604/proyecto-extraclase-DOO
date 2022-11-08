package edu.uco.artdly.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;

import edu.uco.artdly.data.dao.ArtworkTypeDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.ArtworkTypeDTO;

public class ArtworkTypePostgresqlDAO extends DAORelational implements ArtworkTypeDAO {

	public ArtworkTypePostgresqlDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(ArtworkTypeDTO artworkType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ArtworkTypeDTO> find(ArtworkTypeDTO artworkType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ArtworkTypeDTO artworkType) {
		// TODO Auto-generated method stub
		
	}


}
