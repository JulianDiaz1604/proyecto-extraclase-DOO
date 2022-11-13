package edu.uco.artdly.service.usecase.command.implementation;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.UserDTO;
import edu.uco.artdly.service.usecase.command.CreateUserCommand;
import edu.uco.artdly.service.usecase.user.CreateUserUsecase;
import edu.uco.artdly.service.usecase.user.implementation.CreateUserUsecaseImpl;

public class CreateUserCommandImpl implements CreateUserCommand{
    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final CreateUserUsecase useCase = new CreateUserUsecaseImpl(factory);

    @Override
    public void execute(UserDTO user) {
        try {
            factory.initTransaction();
            useCase.execute(user);
            factory.confirmTransaction();
        } catch(UsecaseCustomException exception){
            factory.cancelTransaction();
            throw exception;
        } catch(ArtdlyCustomException exception) {
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.wrapException("Se presento un problema al registrar al usuario", exception);
        } catch(final Exception exception){
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.CreateBusinessException(null, exception);
        }        
    }

}
