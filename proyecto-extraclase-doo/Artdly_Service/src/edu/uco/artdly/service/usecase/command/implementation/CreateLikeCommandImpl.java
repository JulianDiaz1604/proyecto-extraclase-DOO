package edu.uco.artdly.service.usecase.command.implementation;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;

import edu.uco.artdly.domain.LikeDTO;

import edu.uco.artdly.service.usecase.command.CreateLikeCommand;
import edu.uco.artdly.service.usecase.like.CreateLikeUsecase;
import edu.uco.artdly.service.usecase.like.implementation.CreateLikeUsecaseImpl;

public class CreateLikeCommandImpl implements CreateLikeCommand{
    
    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final CreateLikeUsecase useCase = new CreateLikeUsecaseImpl(factory);
    @Override
    public void execute(LikeDTO like) {
        try {
            factory.initTransaction();
            useCase.execute(like);
            factory.confirmTransaction();
        } catch(UsecaseCustomException exception){
            factory.cancelTransaction();
            throw exception;
        } catch(ArtdlyCustomException exception) {
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.wrapException(Messages.CreateLikeCommandImpl.TECHNICAL_PROBLEM_CREATE_LIKE , exception);
        } catch(final Exception exception){
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.CreateBusinessException(Messages.CreateLikeCommandImpl.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_LIKE, exception);
        }        
    }
}
