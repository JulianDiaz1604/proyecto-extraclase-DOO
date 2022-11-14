package edu.uco.artdly.service.usecase.command;

import edu.uco.artdly.domain.ArtworkDTO;

public interface CreateArtworkCommand {
    void execute(final ArtworkDTO artwork);
}
