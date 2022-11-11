package edu.uco.artdly.service.usecase.user;

import java.util.UUID;

import edu.uco.artdly.domain.UserDTO;

public interface FindUserByIdUsecase {
    
    UserDTO execute(UUID id);

}
