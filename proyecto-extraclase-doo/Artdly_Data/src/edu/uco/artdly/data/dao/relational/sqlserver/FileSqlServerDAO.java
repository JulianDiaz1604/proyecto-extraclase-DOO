package edu.uco.artdly.data.dao.relational.sqlserver;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import edu.uco.artdly.data.dao.FileDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.FileDTO;

public class FileSqlServerDAO extends DAORelational implements FileDAO {

	protected FileSqlServerDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(FileDTO file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<FileDTO> find(FileDTO file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(FileDTO file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
