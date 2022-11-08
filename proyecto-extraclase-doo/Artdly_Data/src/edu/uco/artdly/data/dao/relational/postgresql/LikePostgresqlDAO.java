package edu.uco.artdly.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import edu.uco.artdly.data.dao.LikeDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.LikeDTO;

public class LikePostgresqlDAO extends DAORelational implements LikeDAO {

	public LikePostgresqlDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(LikeDTO like) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<LikeDTO> find(LikeDTO like) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
