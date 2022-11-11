package edu.uco.artdly.service.usecase.file.implementation;

import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.FileDTO;
import edu.uco.artdly.domain.FileTypeDTO;
import edu.uco.artdly.service.usecase.file.CreateFileUseCase;
import edu.uco.artdly.service.usecase.file.CreatePathFileUseCase;
import edu.uco.artdly.service.usecase.fileType.FindFileTypeByName;
import edu.uco.artdly.service.usecase.fileType.implementation.FindFileTypeByNameImpl;

public class CreateFileUseCaseImpl implements CreateFileUseCase {

    private final DAOFactory factory;
    private final CreatePathFileUseCase createPathFileUseCase;
    private final FindFileTypeByName findFileTypeByName;

    public CreateFileUseCaseImpl(DAOFactory factory){
        this.factory = factory;
        this.createPathFileUseCase = new CreatePathFileUseCaseImpl();
        this.findFileTypeByName = new FindFileTypeByNameImpl(factory);
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
