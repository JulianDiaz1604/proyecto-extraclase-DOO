package edu.uco.artdly.service.usecase.command;


import java.util.UUID;

import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.UserDTO;


public interface FindLikeByUserAndArtworkCommand {
    boolean execute(UUID  user, UUID artwork);

}
