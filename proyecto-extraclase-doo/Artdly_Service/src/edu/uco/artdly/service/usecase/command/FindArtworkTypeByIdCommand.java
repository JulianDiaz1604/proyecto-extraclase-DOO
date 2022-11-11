package edu.uco.artdly.service.usecase.command;

import java.util.UUID;

import edu.uco.artdly.domain.ArtworkTypeDTO;

public interface FindArtworkTypeByIdCommand {
    ArtworkTypeDTO execute(UUID id);
}
