package edu.uco.artdly.service.usecase.file.implementation;

import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.FileDTO;
import edu.uco.artdly.domain.FileTypeDTO;
import edu.uco.artdly.service.usecase.file.CreateFileUsecase;
import edu.uco.artdly.service.usecase.file.CreatePathFileUsecase;
import edu.uco.artdly.service.usecase.fileType.FindFileTypeByNameUsecase;
import edu.uco.artdly.service.usecase.fileType.implementation.FindFileTypeByNameUsecaseImpl;

public class CreateFileUsecaseImpl implements CreateFileUsecase {

    private final DAOFactory factory;
    private final CreatePathFileUsecase createPathFileUseCase;
    private final FindFileTypeByNameUsecase findFileTypeByName;

    public CreateFileUsecaseImpl(DAOFactory factory){
        this.factory = factory;
        this.createPathFileUseCase = new CreatePathFileUsecaseImpl();
        this.findFileTypeByName = new FindFileTypeByNameUsecaseImpl(factory);
    }

    @Override
    public FileDTO execute(FileDTO file) {
        String pathName = createPathFileUseCase.execute(file.getPathFile());
        FileTypeDTO fileType = findFileType(file.getTypeFile().getFileType());

        file.setPathFile(pathName);
        file.setTypeFile(fileType);

        factory.getFileDAO().create(file);

        return file;
    }

    private FileTypeDTO findFileType(String fileType){
        final FileTypeDTO fType = findFileTypeByName.execute(fileType);

        if(fType.notExist()){ //TODO create message
            throw UsecaseCustomException.CreateUserException("No existe el tipo de archivo");
        }

        return fType;
    }
    
}
