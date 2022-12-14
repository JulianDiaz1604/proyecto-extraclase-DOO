package edu.uco.artdly.service.usecase.user;

import edu.uco.artdly.domain.UserDTO;

public interface FindUserByUsernameUsecase {
    UserDTO execute(String username);

}
