package edu.uco.artdly.service.usecase.command;

import java.util.List;

import edu.uco.artdly.domain.ArtworkDTO;

public interface FindAllArtworkCommand {
    List<ArtworkDTO> execute();


}
