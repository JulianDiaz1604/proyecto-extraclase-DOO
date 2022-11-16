package edu.uco.artdly.service.usecase.command;


import java.util.UUID;



public interface FindLikeByUserAndArtworkCommand {
    boolean execute(UUID  user, UUID artwork);

}
