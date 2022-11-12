package edu.uco.artdly.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.artdly.domain.ArtworkTypeDTO;

public interface ArtworkTypeDAO {
	void create(ArtworkTypeDTO artworkType);

	List<ArtworkTypeDTO> find(ArtworkTypeDTO artworkType);

	void update(ArtworkTypeDTO artworkType);

	void delete(UUID id);

   List<ArtworkTypeDTO> findAll();
}
