package edu.uco.artdly.service.usecase.category;

import java.util.List;

import edu.uco.artdly.domain.CategoryDTO;

public interface FindAllCategoryUsecase {
    List<CategoryDTO> execute();

}
