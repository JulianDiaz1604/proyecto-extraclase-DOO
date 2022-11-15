package edu.uco.artdly.service.usecase.fileType.implementation;

import java.util.List;

import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.FileTypeDTO;
import edu.uco.artdly.service.usecase.fileType.FindAllFileTypeUsecase;

public class FindAllFileTypeUsecaseImpl implements FindAllFileTypeUsecase{
    private DAOFactory factory;
    
    public FindAllFileTypeUsecaseImpl(DAOFactory factory) {
        this.factory = factory;
    }

    
    @Override
    public List<FileTypeDTO> execute() {
        return factory.getFileTypeDAO().findAll();
    }

}
