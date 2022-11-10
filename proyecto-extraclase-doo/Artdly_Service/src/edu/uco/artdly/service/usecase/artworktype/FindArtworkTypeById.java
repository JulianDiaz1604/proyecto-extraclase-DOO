package edu.uco.artdly.service.usecase.artworktype;

import java.util.UUID;

import edu.uco.artdly.domain.ArtworkTypeDTO;

public interface FindArtworkTypeById {
    
    ArtworkTypeDTO execute(UUID id);

}
