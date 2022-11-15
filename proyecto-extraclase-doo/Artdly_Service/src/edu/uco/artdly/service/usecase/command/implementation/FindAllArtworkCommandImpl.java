package edu.uco.artdly.service.usecase.command.implementation;

import java.util.List;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.service.usecase.artwork.FindAllArtworkUsecase;
import edu.uco.artdly.service.usecase.artwork.implementation.FindAllArtworkUsecaseImpl;
import edu.uco.artdly.service.usecase.command.FindAllArtworkCommand;



public class FindAllArtworkCommandImpl implements FindAllArtworkCommand{
    
    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final FindAllArtworkUsecase useCase = new FindAllArtworkUsecaseImpl(factory);

    @Override
    public List<ArtworkDTO> execute() {
        try {
            factory.initTransaction();
            List<ArtworkDTO> artwork = useCase.execute();
            factory.confirmTransaction();
            return artwork;
        } catch(UsecaseCustomException exception){
            factory.cancelTransaction();
            throw exception;
        } catch(ArtdlyCustomException exception) {
            factory.cancelTransaction(); 
            throw UsecaseCustomException.wrapException(Messages.FindAllArtworkCommandImpl.TECHNICAL_PROBLEM_FIND_ARTWORK, exception);
        } catch(final Exception exception){
            factory.cancelTransaction(); 
            throw UsecaseCustomException.CreateBusinessException(Messages.FindAllArtworkCommandImpl.TECHNICAL_UNEXPECTED_PROBLEM_FIND_ARTWORK, exception);
        }/* finally {
            factory.closeConection();
        }  */      
    }

}
