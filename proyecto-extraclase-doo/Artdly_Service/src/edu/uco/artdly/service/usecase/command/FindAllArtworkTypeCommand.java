package edu.uco.artdly.service.usecase.command;

import java.util.List;

import edu.uco.artdly.domain.ArtworkTypeDTO;

public interface FindAllArtworkTypeCommand {
    List<ArtworkTypeDTO> execute();

}
