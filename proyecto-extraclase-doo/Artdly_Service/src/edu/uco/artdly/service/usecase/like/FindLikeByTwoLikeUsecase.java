package edu.uco.artdly.service.usecase.like;

import java.util.UUID;

import edu.uco.artdly.domain.LikeDTO;

public interface FindLikeByTwoLikeUsecase {
    LikeDTO execute(UUID userid, UUID artworkid);

}
