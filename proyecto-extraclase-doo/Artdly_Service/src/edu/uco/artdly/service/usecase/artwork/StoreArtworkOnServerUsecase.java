package edu.uco.artdly.service.usecase.artwork;

import edu.uco.artdly.domain.ArtworkDTO;

public interface StoreArtworkOnServerUsecase {
    void execute(ArtworkDTO artwork, String file);
}
