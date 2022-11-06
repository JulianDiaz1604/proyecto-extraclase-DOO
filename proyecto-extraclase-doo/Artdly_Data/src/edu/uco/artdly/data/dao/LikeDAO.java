package edu.uco.artdly.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.artdly.domain.LikeDTO;

public interface LikeDAO {
	void create(LikeDTO like);

	List<LikeDTO> find(LikeDTO like);


	void delete(UUID id);

}
