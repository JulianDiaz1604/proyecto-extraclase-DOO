package edu.uco.artdly.service.usecase.command.implementation;

import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.service.usecase.artwork.FindArtworkByIdUsecase;
import edu.uco.artdly.service.usecase.artwork.implementation.FindArtworkByIdUsecaseImpl;
import edu.uco.artdly.service.usecase.command.FindArtworkByIdCommand;

public class FindArtworkByIdCommandImpl implements FindArtworkByIdCommand {
    
    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final FindArtworkByIdUsecase useCase = new FindArtworkByIdUsecaseImpl(factory);

    @Override
    public ArtworkDTO execute(UUID id) {
        try {
            factory.initTransaction();
            ArtworkDTO artwork = useCase.execute(id);
            factory.confirmTransaction();
            return artwork;
        } catch(UsecaseCustomException exception){
            factory.cancelTransaction();
            throw exception;
        } catch(ArtdlyCustomException exception) {
            factory.cancelTransaction(); 
            throw UsecaseCustomException.wrapException(Messages.FindArtworkByIdCommandImpl.TECHNICAL_PROBLEM_FIND_ARTWORKBYID, exception);
        } catch(final Exception exception){
            factory.cancelTransaction(); 
            throw UsecaseCustomException.CreateBusinessException(Messages.FindArtworkByIdCommandImpl.TECHNICAL_UNEXPECTED_PROBLEM_FIND_ARTWORKBYID, exception);
        }
    }

}
