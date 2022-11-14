package edu.uco.artdly.service.usecase.command;


import edu.uco.artdly.domain.LikeDTO;


public interface CreateLikeCommand {
    void execute(final LikeDTO like);

}
