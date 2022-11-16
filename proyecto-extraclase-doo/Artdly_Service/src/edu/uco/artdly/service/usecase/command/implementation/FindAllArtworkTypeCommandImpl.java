package edu.uco.artdly.service.usecase.command.implementation;

import java.util.List;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.service.usecase.artworktype.FindAllArtworkTypeUsecase;
import edu.uco.artdly.service.usecase.artworktype.implementation.FindAllArtworkTypesUsecaseImpl;
import edu.uco.artdly.service.usecase.command.FindAllArtworkTypeCommand;



public class FindAllArtworkTypeCommandImpl implements FindAllArtworkTypeCommand {
    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final FindAllArtworkTypeUsecase useCase = new FindAllArtworkTypesUsecaseImpl(factory);

    @Override
    public List<ArtworkTypeDTO> execute() {
        try {
            factory.initTransaction();
            List<ArtworkTypeDTO> artworkType = useCase.exexute();
            factory.confirmTransaction();
            return artworkType;
        } catch(UsecaseCustomException exception){
            factory.cancelTransaction();
            throw exception;
        } catch(ArtdlyCustomException exception) {
            factory.cancelTransaction(); 
            throw UsecaseCustomException.wrapException(Messages.FindAllArtworkTypeCommandImpl.TECHNICAL_PROBLEM_FIND_ARTWORKTYPE, exception);
        } catch(final Exception exception){
            factory.cancelTransaction(); 
            throw UsecaseCustomException.CreateBusinessException(Messages.FindAllArtworkTypeCommandImpl.TECHNICAL_UNEXPECTED_PROBLEM_FIND_ARTWORKTYPE, exception);
        }/*finally {
            factory.closeConection();
        }*/
    }

}
