package edu.uco.artdly.service.usecase.command;

import java.util.List;

import edu.uco.artdly.domain.CategoryDTO;

public interface FindAllCategoryCommand {
    List<CategoryDTO> execute();

}
