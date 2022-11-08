package edu.uco.artdly.data.dao;

import java.util.List;

import edu.uco.artdly.domain.ArtworkTypeDTO;

public interface ArtworkTypeDAO {
	void create(ArtworkTypeDTO artworkType);

	List<ArtworkTypeDTO> find(ArtworkTypeDTO artworkType);

	void update(ArtworkTypeDTO artworkType);

}
