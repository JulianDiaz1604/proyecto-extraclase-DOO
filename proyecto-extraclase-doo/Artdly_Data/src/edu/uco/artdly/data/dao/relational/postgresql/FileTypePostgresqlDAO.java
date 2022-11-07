package edu.uco.artdly.data.dao.relational.postgresql;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import edu.uco.artdly.data.dao.FileTypeDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.FileTypeDTO;

public class FileTypePostgresqlDAO extends DAORelational implements FileTypeDAO {

	protected FileTypePostgresqlDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(FileTypeDTO filetype) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<FileTypeDTO> find(FileTypeDTO filetype) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(FileTypeDTO filetype) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
