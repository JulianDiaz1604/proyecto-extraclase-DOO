package edu.uco.artdly.data.dao;

import java.util.List;
import java.util.UUID;

import edu.uco.artdly.domain.UserDTO;

public interface UserDAO {
	void create(UserDTO user);

	List<UserDTO> find(UserDTO user);

	void update(UserDTO user);

	void delete(UUID id);

}
