package edu.uco.artdly.service.usecase.command.implementation;

import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.service.usecase.artworktype.FindArtworkTypeByIdUsecase;
import edu.uco.artdly.service.usecase.artworktype.implementation.FindArtworkTypeByIdUsecaseImpl;
import edu.uco.artdly.service.usecase.command.FindArtworkTypeByIdCommand;

public class FindArtworktypeByIdCommandImpl implements FindArtworkTypeByIdCommand {

    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final FindArtworkTypeByIdUsecase useCase = new FindArtworkTypeByIdUsecaseImpl(factory);
    @Override
    public ArtworkTypeDTO execute(UUID id) {
        try {
            factory.initTransaction();
            ArtworkTypeDTO artworkType = useCase.execute(id);
            factory.confirmTransaction();
            return artworkType;
        } catch(UsecaseCustomException exception){
            factory.cancelTransaction();
            throw exception;
        } catch(ArtdlyCustomException exception) {
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.wrapException(Messages.FindArtworktypeByIdCommandImpl.TECHNICAL_PROBLEM_FIND_ARTWORKTYPEBYID, exception);
        } catch(final Exception exception){
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.CreateBusinessException(Messages.FindArtworktypeByIdCommandImpl.TECHNICAL_UNEXPECTED_PROBLEM_FIND_ARTWORKTYPEBYID, exception);
        } finally {
            factory.closeConection();
        }  
    }




}
