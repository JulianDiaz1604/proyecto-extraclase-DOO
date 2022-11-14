package edu.uco.artdly.service.usecase.category.implementation;

import java.util.List;

import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.CategoryDTO;
import edu.uco.artdly.service.usecase.category.FindAllCategoriesUsecase;

public class FindAllCategoriesUsecaseImpl implements FindAllCategoriesUsecase{

    private DAOFactory factory;

    public FindAllCategoriesUsecaseImpl(DAOFactory factory){
        this.factory = factory;
    }
    @Override
    public List<CategoryDTO> execute() {
        return factory.getCategoryDAO().findAllCategories();
    }   
}


