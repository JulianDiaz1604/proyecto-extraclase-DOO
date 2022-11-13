package edu.uco.artdly.service.usecase.like;

import edu.uco.artdly.domain.LikeDTO;

public interface CreateLikeUsecase {
    void execute(LikeDTO like);
}