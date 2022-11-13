package edu.uco.artdly.service.usecase.like;

import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.LikeDTO;
import edu.uco.artdly.domain.UserDTO;

public interface FindLikeByUserUsecase {
    LikeDTO execute(UserDTO user, ArtworkDTO artwork);

}
