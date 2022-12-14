package edu.uco.artdly.service.usecase.fileType.implementation;

import java.util.List;

import edu.uco.artdly.crosscutting.helper.UUIDHelper;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.FileTypeDTO;
import edu.uco.artdly.service.usecase.fileType.FindFileTypeByNameUsecase;

public class FindFileTypeByNameUsecaseImpl implements FindFileTypeByNameUsecase{

    private final DAOFactory factory;

    public FindFileTypeByNameUsecaseImpl(DAOFactory factory){
        this.factory = factory;
    }

    @Override
    public FileTypeDTO execute(String name) {
        
        FileTypeDTO result = new FileTypeDTO();
        final FileTypeDTO artworkType = FileTypeDTO.create(UUIDHelper.getDefaultUUID(null), name);
        final List<FileTypeDTO> results = factory.getFileTypeDAO().find(artworkType);

        if(!results.isEmpty()){
            result = results.get(0);
        }

        return result;
    }
    
}
