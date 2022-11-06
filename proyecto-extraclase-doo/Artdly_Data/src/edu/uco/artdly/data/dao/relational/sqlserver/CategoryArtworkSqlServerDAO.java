package edu.uco.artdly.data.dao.relational.sqlserver;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import edu.uco.artdly.data.dao.CategoryArtworkDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.CategoryArtworkDTO;

public class CategoryArtworkSqlServerDAO extends DAORelational implements CategoryArtworkDAO {

	protected CategoryArtworkSqlServerDAO(Connection connection) {
		super(connection);

	}

	@Override
	public void create(CategoryArtworkDTO categoryArtwork) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CategoryArtworkDTO> find(CategoryArtworkDTO categoryArtwork) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(CategoryArtworkDTO categoryArtwork) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
