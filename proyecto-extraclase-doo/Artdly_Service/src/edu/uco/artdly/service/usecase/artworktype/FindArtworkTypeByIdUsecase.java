package edu.uco.artdly.service.usecase.artworktype;

import java.util.UUID;

import edu.uco.artdly.domain.ArtworkTypeDTO;

public interface FindArtworkTypeByIdUsecase {
    
    ArtworkTypeDTO execute(UUID id);

}
