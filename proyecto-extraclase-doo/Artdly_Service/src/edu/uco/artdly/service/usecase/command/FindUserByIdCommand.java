package edu.uco.artdly.service.usecase.command;

import java.util.UUID;

import edu.uco.artdly.domain.UserDTO;

public interface FindUserByIdCommand {
    UserDTO execute(UUID id);

}
