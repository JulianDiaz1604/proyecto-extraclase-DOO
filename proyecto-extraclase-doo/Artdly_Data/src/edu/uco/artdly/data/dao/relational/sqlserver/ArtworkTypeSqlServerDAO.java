package edu.uco.artdly.data.dao.relational.sqlserver;

import java.sql.Connection;
import java.util.List;

import edu.uco.artdly.data.dao.ArtworkTypeDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.ArtworkTypeDTO;

public class ArtworkTypeSqlServerDAO extends DAORelational implements ArtworkTypeDAO {

	protected ArtworkTypeSqlServerDAO(Connection connection) {
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
