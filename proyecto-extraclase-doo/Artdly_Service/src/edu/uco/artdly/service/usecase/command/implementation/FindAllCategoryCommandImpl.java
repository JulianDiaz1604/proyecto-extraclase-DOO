package edu.uco.artdly.service.usecase.command.implementation;

import java.util.List;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.CategoryDTO;
import edu.uco.artdly.service.usecase.category.FindAllCategoryUsecase;
import edu.uco.artdly.service.usecase.category.implementation.FindAllCategorysUsecaseImpl;
import edu.uco.artdly.service.usecase.command.FindAllCategoryCommand;

public class FindAllCategoryCommandImpl implements FindAllCategoryCommand {
    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final FindAllCategoryUsecase useCase = new FindAllCategorysUsecaseImpl();
    @Override
    public List<CategoryDTO> execute() {
        try {
            factory.initTransaction();
            List<CategoryDTO> category = useCase.execute();
            factory.confirmTransaction();
            return category;
        } catch(UsecaseCustomException exception){
            factory.cancelTransaction();
            throw exception;
        } catch(ArtdlyCustomException exception) {
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.wrapException("Se presento un problema tratando de crear la obra", exception);
        } catch(final Exception exception){
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.CreateBusinessException(null, exception);
        }
    }

}
