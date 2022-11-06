package edu.uco.artdly.data.dao.relational.sqlserver;

import java.sql.Connection;
import java.util.List;

import edu.uco.artdly.data.dao.CategoryDAO;
import edu.uco.artdly.data.dao.relational.DAORelational;
import edu.uco.artdly.domain.CategoryDTO;

public class CategorySqlServerDAO  extends DAORelational implements CategoryDAO {

	protected CategorySqlServerDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create(CategoryDTO category) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CategoryDTO> find(CategoryDTO category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(CategoryDTO category) {
		// TODO Auto-generated method stub
		
	}


}
