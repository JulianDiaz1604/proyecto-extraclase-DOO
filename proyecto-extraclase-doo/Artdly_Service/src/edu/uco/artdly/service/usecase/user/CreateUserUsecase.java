package edu.uco.artdly.service.usecase.user;

import edu.uco.artdly.domain.UserDTO;

public interface CreateUserUsecase {
    void execute(UserDTO user);
}

