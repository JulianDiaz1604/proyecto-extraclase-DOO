package edu.uco.artdly.service.usecase.artwork;

import java.util.UUID;

import edu.uco.artdly.domain.ArtworkDTO;

public interface FindArtworkById {
    ArtworkDTO execute(UUID id);
}
