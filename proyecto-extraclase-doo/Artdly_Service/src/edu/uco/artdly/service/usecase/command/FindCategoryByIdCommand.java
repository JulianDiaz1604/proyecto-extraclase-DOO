package edu.uco.artdly.service.usecase.command;

import java.util.UUID;

import edu.uco.artdly.domain.CategoryDTO;

public interface FindCategoryByIdCommand {
    CategoryDTO execute(UUID id);

}
