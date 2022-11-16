package edu.uco.artdly.service.usecase.categoryArtwork.implementation;

import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.helper.UUIDHelper;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.CategoryArtworkDTO;
import edu.uco.artdly.domain.CategoryDTO;
import edu.uco.artdly.service.usecase.artwork.FindArtworkByIdUsecase;
import edu.uco.artdly.service.usecase.artwork.implementation.FindArtworkByIdUsecaseImpl;
import edu.uco.artdly.service.usecase.category.FindCategoryByIdUsecase;
import edu.uco.artdly.service.usecase.category.implementation.FindCategoryByIdUsecaseImpl;
import edu.uco.artdly.service.usecase.categoryArtwork.CreateCategoryArtworkUsecase;

public class CreateCategoryArtworkUsecaseImpl implements CreateCategoryArtworkUsecase {

    private final DAOFactory factory;
    private final FindCategoryByIdUsecase findCategoryById;
    private final FindArtworkByIdUsecase findArtworkById;

    public CreateCategoryArtworkUsecaseImpl(DAOFactory factory){
        this.factory = factory;
        this.findCategoryById = new FindCategoryByIdUsecaseImpl(factory);
        this.findArtworkById = new FindArtworkByIdUsecaseImpl(factory);
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

        if(artwork.notExist()){ 
            throw UsecaseCustomException.CreateUserException(Messages.CreateCategoryArtworkUsecaseImpl.TECHNICAL_PROBLEM_CREATE_FINDARTWORK);
        }

        return artwork;
    }

    private final CategoryDTO findCategory(final UUID id){
        final CategoryDTO category = findCategoryById.execute(id);

        if(category.notExist()){ 
            throw UsecaseCustomException.CreateUserException(Messages.CreateCategoryArtworkUsecaseImpl.TECHNICAL_PROBLEM_CREATE_FINDCATEOGRY);
        }

        return category;
    }
    
}
