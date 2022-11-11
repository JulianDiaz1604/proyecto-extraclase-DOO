package edu.uco.artdly.service.usecase.command;

import java.util.UUID;

import edu.uco.artdly.domain.ArtworkDTO;

public interface FindArtworkByIdCommand {
    ArtworkDTO execute(UUID id);
}
