package edu.uco.artdly.service.usecase.user;

import java.util.UUID;

import edu.uco.artdly.domain.UserDTO;

public interface FindUserById {
    
    UserDTO execute(UUID id);

}
