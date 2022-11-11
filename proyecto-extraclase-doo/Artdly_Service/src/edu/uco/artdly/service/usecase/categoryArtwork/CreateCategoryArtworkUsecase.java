package edu.uco.artdly.service.usecase.categoryArtwork;

import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.CategoryDTO;

public interface CreateCategoryArtworkUsecase {
    void execute(ArtworkDTO artwork, CategoryDTO category);
}
