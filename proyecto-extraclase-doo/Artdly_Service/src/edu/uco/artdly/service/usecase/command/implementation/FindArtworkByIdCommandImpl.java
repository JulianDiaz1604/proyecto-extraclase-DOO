package edu.uco.artdly.service.usecase.command.implementation;

import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.service.usecase.artwork.FindArtworkById;
import edu.uco.artdly.service.usecase.artwork.implementation.FindArtworkByIdImpl;
import edu.uco.artdly.service.usecase.command.FindArtworkByIdCommand;

public class FindArtworkByIdCommandImpl implements FindArtworkByIdCommand {
    
    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final FindArtworkById useCase = new FindArtworkByIdImpl(factory);

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
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.wrapException("Se presento un problema tratando de crear la obra", exception);
        } catch(final Exception exception){
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.CreateBusinessException(null, exception);
        }
    }

}
