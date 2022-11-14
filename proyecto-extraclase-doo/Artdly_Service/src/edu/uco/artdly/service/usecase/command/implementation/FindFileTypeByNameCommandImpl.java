package edu.uco.artdly.service.usecase.command.implementation;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.FileTypeDTO;
import edu.uco.artdly.service.usecase.command.FindFileTypeByNameCommand;
import edu.uco.artdly.service.usecase.fileType.FindFileTypeByNameUsecase;
import edu.uco.artdly.service.usecase.fileType.implementation.FindFileTypeByNameUsecaseImpl;

public class FindFileTypeByNameCommandImpl implements FindFileTypeByNameCommand{

    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final FindFileTypeByNameUsecase useCase = new FindFileTypeByNameUsecaseImpl(factory);

    @Override
    public FileTypeDTO execute(String name) {
        try {
            factory.initTransaction();
            FileTypeDTO fileType = useCase.execute(name);
            factory.confirmTransaction();
            return fileType;
        } catch(UsecaseCustomException exception){
            factory.cancelTransaction();
            throw exception;
        } catch(ArtdlyCustomException exception) {
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.wrapException("Se presento un problema tratando de crear la obra", exception);
        } catch(final Exception exception){
            factory.cancelTransaction(); //TODO: create message
            throw UsecaseCustomException.CreateBusinessException(null, exception);
        } finally {
            factory.closeConection();
        }      
    }

}
