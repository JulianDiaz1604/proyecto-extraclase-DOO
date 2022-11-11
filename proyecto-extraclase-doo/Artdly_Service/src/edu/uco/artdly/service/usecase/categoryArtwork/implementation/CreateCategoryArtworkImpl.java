package edu.uco.artdly.service.usecase.categoryArtwork.implementation;

import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.helper.UUIDHelper;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.CategoryArtworkDTO;
import edu.uco.artdly.domain.CategoryDTO;
import edu.uco.artdly.service.usecase.artwork.FindArtworkById;
import edu.uco.artdly.service.usecase.artwork.implementation.FindArtworkByIdImpl;
import edu.uco.artdly.service.usecase.category.FindCategoryById;
import edu.uco.artdly.service.usecase.category.implementation.FindCategoryByIdImpl;
import edu.uco.artdly.service.usecase.categoryArtwork.CreateCategoryArtwork;

public class CreateCategoryArtworkImpl implements CreateCategoryArtwork {

    private final DAOFactory factory;
    private final FindCategoryById findCategoryById;
    private final FindArtworkById findArtworkById;

    public CreateCategoryArtworkImpl(DAOFactory factory){
        this.factory = factory;
        this.findCategoryById = new FindCategoryByIdImpl(factory);
        this.findArtworkById = new FindArtworkByIdImpl(factory);
    }

    @Override
    public void execute(ArtworkDTO artworkDTO, CategoryDTO categoryDTO) {
        ArtworkDTO artwork = findArtwork(artworkDTO.getId());
        CategoryDTO category = findCategory(categoryDTO.getId());

        CategoryArtworkDTO categoryArtwork = new CategoryArtworkDTO(UUIDHelper.getNewUUID(), artwork, category);

        factory.getCategoryArtworkDAO().create(categoryArtwork);
    }

    private final ArtworkDTO findArtwork(final UUID id){
        final ArtworkDTO artwork = findArtworkById.execute(id);

        if(artwork.notExist()){ //TODO create message
            throw UsecaseCustomException.CreateUserException("No existe la categoria");
        }

        return artwork;
    }

    private final CategoryDTO findCategory(final UUID id){
        final CategoryDTO category = findCategoryById.execute(id);

        if(category.notExist()){ //TODO create message
            throw UsecaseCustomException.CreateUserException("No existe la categoria");
        }

        return category;
    }
    
}
