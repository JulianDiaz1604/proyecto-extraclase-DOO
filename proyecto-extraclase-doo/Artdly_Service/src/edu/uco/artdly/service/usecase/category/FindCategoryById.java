package edu.uco.artdly.service.usecase.category;

import java.util.UUID;

import edu.uco.artdly.domain.CategoryDTO;

public interface FindCategoryById {
    CategoryDTO execute(UUID id);
}
