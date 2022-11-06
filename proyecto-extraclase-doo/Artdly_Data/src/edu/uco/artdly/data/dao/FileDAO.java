package edu.uco.artdly.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.artdly.domain.FileDTO;

public interface FileDAO {
	void create(FileDTO file);

	List<FileDTO> find(FileDTO file);

	void update(FileDTO file);

	void delete(UUID id);

}
