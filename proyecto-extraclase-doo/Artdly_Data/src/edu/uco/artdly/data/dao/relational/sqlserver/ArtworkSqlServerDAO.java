package edu.uco.artdly.data.dao.relational.sqlserver;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import edu.uco.artdly.data.dao.ArtworkDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.ArtworkDTO;

public class ArtworkSqlServerDAO  extends DAORelational implements ArtworkDAO {

	protected ArtworkSqlServerDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(ArtworkDTO artwork) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<ArtworkDTO> find(ArtworkDTO artwork) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(ArtworkDTO artwork) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
