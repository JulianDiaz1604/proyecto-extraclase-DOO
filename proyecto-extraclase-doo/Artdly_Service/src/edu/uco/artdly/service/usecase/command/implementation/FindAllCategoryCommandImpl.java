package edu.uco.artdly.service.usecase.command.implementation;

import java.util.List;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.CategoryDTO;
import edu.uco.artdly.service.usecase.category.FindAllCategoriesUsecase;
import edu.uco.artdly.service.usecase.category.implementation.FindAllCategoriesUsecaseImpl;
import edu.uco.artdly.service.usecase.command.FindAllCategoryCommand;

public class FindAllCategoryCommandImpl implements FindAllCategoryCommand {
    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final FindAllCategoriesUsecase useCase = new FindAllCategoriesUsecaseImpl(factory);

    @Override
    public List<CategoryDTO> execute() {
        try {
            factory.initTransaction();
            List<CategoryDTO> categories = useCase.execute();
            factory.confirmTransaction();
            return categories;
        } catch(UsecaseCustomException exception){
            factory.cancelTransaction();
            throw exception;
        } catch(ArtdlyCustomException exception) {
            factory.cancelTransaction(); 
            throw UsecaseCustomException.wrapException(Messages.FindAllCategoryCommandImpl.TECHNICAL_PROBLEM_FIND_CATEGORY, exception);
        } catch(final Exception exception){
            factory.cancelTransaction(); 
            throw UsecaseCustomException.CreateBusinessException(Messages.FindAllCategoryCommandImpl.TECHNICAL_UNEXPECTED_PROBLEM_FIND_CATEGORY, exception);
        } 
    }

}
