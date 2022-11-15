package edu.uco.artdly.service.usecase.command.implementation;

import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.service.usecase.command.DeleteLikeCommand;
import edu.uco.artdly.service.usecase.like.DeleteLikeUsecase;
import edu.uco.artdly.service.usecase.like.implementation.DeleteLikeUsecaseImpl;
public class DeleteLikeCommandImpl implements DeleteLikeCommand {
    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final DeleteLikeUsecase useCase = new DeleteLikeUsecaseImpl(factory);
    @Override
    public void execute(UUID id) {
        try {
            factory.initTransaction();
            useCase.execute(id);
            factory.confirmTransaction();
        } catch(UsecaseCustomException exception){
            factory.cancelTransaction();
            throw exception;
        } catch(ArtdlyCustomException exception) {
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.wrapException(Messages.DeleteLikeCommandImpl.TECHNICAL_PROBLEM_CREATE_DELETELIKE, exception);
        } catch(final Exception exception){
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.CreateBusinessException(Messages.DeleteLikeCommandImpl.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_DELETELIKE, exception);
        }        
    }

}
