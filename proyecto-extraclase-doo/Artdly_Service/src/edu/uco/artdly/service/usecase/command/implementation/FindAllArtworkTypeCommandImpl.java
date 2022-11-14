package edu.uco.artdly.service.usecase.command.implementation;

import java.util.List;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.service.usecase.artworktype.FindAllArtworkTypeUsecase;
import edu.uco.artdly.service.usecase.artworktype.implementation.FindAllArtworkTypesUsecaseImpl;
import edu.uco.artdly.service.usecase.command.FindAllArtworkTypeCommand;



public class FindAllArtworkTypeCommandImpl implements FindAllArtworkTypeCommand {
    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
    private final FindAllArtworkTypeUsecase useCase = new FindAllArtworkTypesUsecaseImpl(factory);

    @Override
    public List<ArtworkTypeDTO> exexute() {
        try {
            factory.initTransaction();
            List<ArtworkTypeDTO> artworkType = useCase.exexute();
            factory.confirmTransaction();
            return artworkType;
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
