package edu.uco.artdly.service.usecase.command;

import edu.uco.artdly.domain.ArtworkDTO;

public interface PostArtworkCommand {
    void execute(final ArtworkDTO artwork, String pathFile);
}
