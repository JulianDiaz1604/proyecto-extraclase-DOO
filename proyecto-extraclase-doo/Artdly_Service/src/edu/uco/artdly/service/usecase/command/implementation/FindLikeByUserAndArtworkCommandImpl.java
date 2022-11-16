package edu.uco.artdly.service.usecase.command.implementation;

import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.LikeDTO;
import edu.uco.artdly.service.usecase.command.FindLikeByUserAndArtworkCommand;
import edu.uco.artdly.service.usecase.like.FindLikeByTwoLikeUsecase;
import edu.uco.artdly.service.usecase.like.implementation.FindLikeByTwoIdUsecaseImpl;


public class FindLikeByUserAndArtworkCommandImpl implements FindLikeByUserAndArtworkCommand {
    
    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final FindLikeByTwoLikeUsecase useCase = new FindLikeByTwoIdUsecaseImpl(factory);

    @Override
    public boolean execute(UUID user, UUID artwork) {
        try {
            factory.initTransaction();
            LikeDTO like = useCase.execute(user, artwork);
            factory.confirmTransaction();
            if(like.exist()) {
                return true;
            }else {
            return false ;}
        } catch(UsecaseCustomException exception){
            factory.cancelTransaction();
            throw exception;
        } catch(ArtdlyCustomException exception) {
            factory.cancelTransaction(); 
            throw UsecaseCustomException.wrapException(Messages.FindArtworktypeByIdCommandImpl.TECHNICAL_PROBLEM_FIND_ARTWORKTYPEBYID, exception);
        } catch(final Exception exception){
            factory.cancelTransaction(); 
            throw UsecaseCustomException.CreateBusinessException(Messages.FindArtworktypeByIdCommandImpl.TECHNICAL_UNEXPECTED_PROBLEM_FIND_ARTWORKTYPEBYID, exception);
        } finally {
            factory.closeConection();
        }  
        
        
    }

}
