package edu.uco.artdly.service.usecase.command.implementation;

import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.UserDTO;
import edu.uco.artdly.service.usecase.command.FindUserByIdCommand;
import edu.uco.artdly.service.usecase.user.FindUserByIdUsecase;
import edu.uco.artdly.service.usecase.user.implementation.FindUserByIdUsecaseImpl;

public class FindUserByIdCommandImpl implements FindUserByIdCommand{

    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final FindUserByIdUsecase useCase = new FindUserByIdUsecaseImpl(factory);

    @Override
    public UserDTO execute(UUID id) {
        try {
            factory.initTransaction();
            UserDTO user = useCase.execute(id);
            factory.confirmTransaction();
            return user;
        } catch(UsecaseCustomException exception){
            factory.cancelTransaction();
            throw exception;
        } catch(ArtdlyCustomException exception) {
            factory.cancelTransaction(); 
            throw UsecaseCustomException.wrapException(Messages.FindUserByIdCommandImpl.TECHNICAL_PROBLEM_FIND_USERBYID, exception);
        } catch(final Exception exception){
            factory.cancelTransaction(); 
            throw UsecaseCustomException.CreateBusinessException(Messages.FindUserByIdCommandImpl.TECHNICAL_UNEXPECTED_PROBLEM_FIND_USERBYID, exception);
        }  
    }

}
