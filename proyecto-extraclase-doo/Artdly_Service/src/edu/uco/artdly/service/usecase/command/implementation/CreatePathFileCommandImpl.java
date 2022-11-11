package edu.uco.artdly.service.usecase.command.implementation;

import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.data.enumeration.DAOFactoryType;
import edu.uco.artdly.domain.FileDTO;
import edu.uco.artdly.service.usecase.artwork.CreateArtworkUsecase;
import edu.uco.artdly.service.usecase.artwork.implementation.CreateArtworkUsecaseImpl;
import edu.uco.artdly.service.usecase.command.CreatePathFileCommand;
import edu.uco.artdly.service.usecase.file.CreatePathFileUsecase;
import edu.uco.artdly.service.usecase.file.implementation.CreatePathFileUsecaseImpl;

public class CreatePathFileCommandImpl implements CreatePathFileCommand {

    private final DAOFactory factory = DAOFactory.getDAOFactory(DAOFactoryType.POSTGRESQL);
  
    //private final CreatePathFileUsecase useCase = new CreatePathFileUsecaseImpl(factory);

    @Override
    public void execute(FileDTO pathFile) {
        // TODO Auto-generated method stub
        
    }

}
