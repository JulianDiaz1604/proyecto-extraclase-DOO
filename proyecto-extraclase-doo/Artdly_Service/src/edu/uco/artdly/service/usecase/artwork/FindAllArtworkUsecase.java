package edu.uco.artdly.service.usecase.artwork;

import java.util.List;

import edu.uco.artdly.domain.ArtworkDTO;

public interface FindAllArtworkUsecase {
    List<ArtworkDTO> execute();
}
