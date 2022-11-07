package edu.uco.artdly.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import edu.uco.artdly.data.dao.CommentDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.CommentDTO;

public class CommentPostgresqlDAO extends DAORelational implements CommentDAO {

	protected CommentPostgresqlDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(CommentDTO comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CommentDTO> find(CommentDTO comment) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
