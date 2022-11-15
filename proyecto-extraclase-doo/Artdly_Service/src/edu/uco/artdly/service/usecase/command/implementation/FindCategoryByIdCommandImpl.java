package edu.uco.artdly.service.usecase.command.implementation;

import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.CategoryDTO;
import edu.uco.artdly.service.usecase.category.FindCategoryByIdUsecase;
import edu.uco.artdly.service.usecase.category.implementation.FindCategoryByIdUsecaseImpl;
import edu.uco.artdly.service.usecase.command.FindCategoryByIdCommand;

public class FindCategoryByIdCommandImpl implements FindCategoryByIdCommand{

    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final FindCategoryByIdUsecase useCase = new FindCategoryByIdUsecaseImpl(factory);

    @Override
    public CategoryDTO execute(UUID id) {
        try {
            factory.initTransaction();
            CategoryDTO category = useCase.execute(id);
            factory.confirmTransaction();
            return category;
        } catch(UsecaseCustomException exception){
            factory.cancelTransaction();
            throw exception;
        } catch(ArtdlyCustomException exception) {
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.wrapException(Messages.FindCategoryByIdCommandImpl.TECHNICAL_PROBLEM_FIND_CATEGORYBYID, exception);
        } catch(final Exception exception){
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.CreateBusinessException(Messages.FindCategoryByIdCommandImpl.TECHNICAL_UNEXPECTED_PROBLEM_FIND_CATEGORYBYID, exception);
        }
    }

}
