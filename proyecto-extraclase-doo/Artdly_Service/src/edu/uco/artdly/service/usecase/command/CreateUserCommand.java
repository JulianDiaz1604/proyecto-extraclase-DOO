package edu.uco.artdly.service.usecase.command;

import edu.uco.artdly.domain.UserDTO;

public interface CreateUserCommand {
    void execute(UserDTO user);

}
