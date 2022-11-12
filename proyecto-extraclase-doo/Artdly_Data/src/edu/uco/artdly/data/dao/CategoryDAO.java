package edu.uco.artdly.data.dao;

import java.util.List;

import edu.uco.artdly.domain.CategoryDTO;

public interface CategoryDAO {
	void create(CategoryDTO category);

	List<CategoryDTO> find(CategoryDTO category);

	void update(CategoryDTO category);
	
	List<CategoryDTO> findAll();
}
