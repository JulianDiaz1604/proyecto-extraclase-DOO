package edu.uco.artdly.service.usecase.category.implementation;

import java.util.List;
import java.util.UUID;

import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.CategoryDTO;
import edu.uco.artdly.service.usecase.category.FindCategoryByIdUsecase;

public class FindCategoryByIdUsecaseImpl implements FindCategoryByIdUsecase {

    private final DAOFactory factory;

    public FindCategoryByIdUsecaseImpl(DAOFactory factory){
        this.factory = factory;
    }

    @Override
    public CategoryDTO execute(UUID id) {
        
        CategoryDTO result = new CategoryDTO();
        final CategoryDTO category = CategoryDTO.create(id);
        final List<CategoryDTO> results = factory.getCategoryDAO().find(category);

        if(!results.isEmpty()){
            result = results.get(0);
        }

        return result;

    }
    
}
