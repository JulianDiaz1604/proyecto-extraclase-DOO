package edu.uco.artdly.service.usecase.like;

import java.util.UUID;

import edu.uco.artdly.domain.LikeDTO;

public interface FindLikeByIdUsecase {
    LikeDTO execute(UUID id);

}
