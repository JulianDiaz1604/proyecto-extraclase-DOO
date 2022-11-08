package edu.uco.artdly.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.artdly.domain.FileTypeDTO;

public interface FileTypeDAO {
	
	void create(FileTypeDTO filetype);

	List<FileTypeDTO> find(FileTypeDTO filetype);

	void update(FileTypeDTO filetype);

	void delete(UUID id);

}
