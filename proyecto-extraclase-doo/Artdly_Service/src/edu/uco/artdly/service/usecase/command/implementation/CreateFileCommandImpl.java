package edu.uco.artdly.service.usecase.command.implementation;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.FileDTO;
import edu.uco.artdly.service.usecase.command.CreateFileCommand;
import edu.uco.artdly.service.usecase.file.CreateFileUsecase;
import edu.uco.artdly.service.usecase.file.implementation.CreateFileUsecaseImpl;

public class CreateFileCommandImpl implements CreateFileCommand {

    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final CreateFileUsecase useCase = new CreateFileUsecaseImpl(factory);

    @Override
    public void execute(FileDTO file) {
        try {
            factory.initTransaction();
            useCase.execute(file);
            factory.confirmTransaction();
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

