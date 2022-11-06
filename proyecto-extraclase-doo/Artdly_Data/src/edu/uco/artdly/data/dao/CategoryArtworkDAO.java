package edu.uco.artdly.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.artdly.domain.CategoryArtworkDTO;

public interface CategoryArtworkDAO {
	void create(CategoryArtworkDTO categoryArtwork);

	List<CategoryArtworkDTO> find(CategoryArtworkDTO categoryArtwork);

	void update(CategoryArtworkDTO categoryArtwork);

	void delete(UUID id);

}
