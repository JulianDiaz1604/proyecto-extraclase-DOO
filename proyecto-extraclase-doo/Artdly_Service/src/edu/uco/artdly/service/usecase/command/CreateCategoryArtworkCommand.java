package edu.uco.artdly.service.usecase.command;

import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.CategoryDTO;

public interface CreateCategoryArtworkCommand {
    void execute(final ArtworkDTO artwork,final CategoryDTO category);
}
