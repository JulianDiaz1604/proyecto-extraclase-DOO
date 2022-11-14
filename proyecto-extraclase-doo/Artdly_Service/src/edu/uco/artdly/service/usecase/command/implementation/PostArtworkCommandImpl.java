package edu.uco.artdly.service.usecase.command.implementation;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.service.usecase.artwork.CreateArtworkUsecase;
import edu.uco.artdly.service.usecase.artwork.StoreArtworkOnServerUsecase;
import edu.uco.artdly.service.usecase.artwork.implementation.CreateArtworkUsecaseImpl;
import edu.uco.artdly.service.usecase.artwork.implementation.StoreArtworkOnServerUsecaseImpl;
import edu.uco.artdly.service.usecase.command.PostArtworkCommand;

public class PostArtworkCommandImpl implements PostArtworkCommand {

    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final CreateArtworkUsecase createArtworkUseCase = new CreateArtworkUsecaseImpl(factory);
    private final StoreArtworkOnServerUsecase storeArtworkOnServerUsecase = new StoreArtworkOnServerUsecaseImpl();

    @Override
    public void execute(ArtworkDTO artwork, String pathFile) {
        try {
            factory.initTransaction();
            createArtworkUseCase.execute(artwork);
            factory.confirmTransaction();
            storeArtworkOnServerUsecase.execute(artwork, pathFile);
        } catch(UsecaseCustomException exception){
            factory.cancelTransaction();
            throw exception;
        } catch(ArtdlyCustomException exception) {
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.wrapException(Messages.PostArtworkCommandImpl.TECHNICAL_PROBLEM_POST_ARTWORK, exception);
        } catch(final Exception exception){
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.CreateBusinessException(Messages.PostArtworkCommandImpl.TECHNICAL_UNEXPECTED_PROBLEM_POST_ARTWORK, exception);
        } finally {
            factory.closeConection();
        }
    }
    
}
