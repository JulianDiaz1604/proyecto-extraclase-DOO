package edu.uco.artdly.service.usecase.category;

import java.util.List;

import edu.uco.artdly.domain.CategoryDTO;

public interface FindAllCategoriesUsecase {
    List<CategoryDTO> execute();

}
