package edu.uco.artdly.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.artdly.domain.CommentDTO;

public interface CommentDAO {
	void create(CommentDTO comment);

	List<CommentDTO> find(CommentDTO comment);


	void delete(UUID id);

}
