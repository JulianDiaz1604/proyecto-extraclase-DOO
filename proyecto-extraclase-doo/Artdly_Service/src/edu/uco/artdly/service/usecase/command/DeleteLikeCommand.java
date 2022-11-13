package edu.uco.artdly.service.usecase.command;

import edu.uco.artdly.domain.LikeDTO;

public interface DeleteLikeCommand {
    void execute(final LikeDTO like);

}
