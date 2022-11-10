package edu.uco.artdly.service.usecase.artwork;

import edu.uco.artdly.domain.ArtworkDTO;

public interface CreateArtworkUsecase {
    
    void execute(ArtworkDTO artwork);

}
