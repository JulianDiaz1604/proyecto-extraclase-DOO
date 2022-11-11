package edu.uco.artdly.service.usecase.command.implementation;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.CategoryDTO;
import edu.uco.artdly.service.usecase.categoryArtwork.CreateCategoryArtworkUsecase;
import edu.uco.artdly.service.usecase.categoryArtwork.implementation.CreateCategoryArtworkUsecaseImpl;
import edu.uco.artdly.service.usecase.command.CreateCategoryArtworkCommand;

public class CreateCategoryArtworkCommandImpl implements CreateCategoryArtworkCommand{

    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final CreateCategoryArtworkUsecase useCase = new CreateCategoryArtworkUsecaseImpl(factory);
    @Override
    public void execute(ArtworkDTO artwork, CategoryDTO category) {
        try {
            factory.initTransaction();
            useCase.execute(artwork, category);;
            factory.confirmTransaction();
        } catch(UsecaseCustomException exception){
            factory.cancelTransaction();
            throw exception;
        } catch(ArtdlyCustomException exception) {
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.wrapException("Se presento un problema tratando de crear la obra", exception);
        } catch(final Exception exception){
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.CreateBusinessException(null, exception);
        }        
    }
}
    


