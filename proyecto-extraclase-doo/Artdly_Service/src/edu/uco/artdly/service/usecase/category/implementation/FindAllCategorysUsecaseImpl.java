package edu.uco.artdly.service.usecase.category.implementation;

import java.util.List;

import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.CategoryDTO;
import edu.uco.artdly.service.usecase.category.FindAllCategoryUsecase;

public class FindAllCategorysUsecaseImpl implements FindAllCategoryUsecase{

    private DAOFactory factory;

    public FindAllCategorysUsecaseImpl(DAOFactory factory){
        this.factory = factory;
    }
    @Override
    public List<CategoryDTO> execute() {
         final List<CategoryDTO> category = factory.getCategoryDAO().findAll();


        return category;

    }   
}


