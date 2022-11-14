package edu.uco.artdly.service.usecase.command;

import java.util.UUID;


public interface DeleteLikeCommand {
    void execute(UUID id);

}
