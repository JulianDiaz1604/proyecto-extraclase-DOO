package edu.uco.artdly.service.usecase.command.implementation;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.service.usecase.artwork.CreateArtworkUsecase;
import edu.uco.artdly.service.usecase.artwork.implementation.CreateArtworkUsecaseImpl;
import edu.uco.artdly.service.usecase.command.CreateArtworkCommand;

public class CreateArtworkCommandImpl implements CreateArtworkCommand {

    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final CreateArtworkUsecase useCase = new CreateArtworkUsecaseImpl(factory);

    @Override
    public void execute(ArtworkDTO artwork) {
        try {
            factory.initTransaction();
            useCase.execute(artwork);
            factory.confirmTransaction();
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
