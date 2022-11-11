package edu.uco.artdly.service.usecase.file.implementation;

import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.FileDTO;
import edu.uco.artdly.service.usecase.file.CreateFileUseCase;

public class CreateFileUseCaseImpl implements CreateFileUseCase {

    private final DAOFactory factory;

    public CreateFileUseCaseImpl(DAOFactory factory){
        this.factory = factory;
    }

    @Override
    public void execute(FileDTO file) {
        // TODO Auto-generated method stub
        
    }
    
}
