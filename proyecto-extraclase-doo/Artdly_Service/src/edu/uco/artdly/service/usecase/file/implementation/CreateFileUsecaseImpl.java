package edu.uco.artdly.service.usecase.file.implementation;


import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.helper.UUIDHelper;
import edu.uco.artdly.crosscutting.messages.Messages;
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
        FileTypeDTO fileType = validateType(file.getTypeFile());

        file.setId(UUIDHelper.getNewUUID());
        file.setPathFile(pathName);
        file.setTypeFile(fileType);

        factory.getFileDAO().create(file);

        return file;
    }


    private FileTypeDTO validateType(FileTypeDTO type){
        final FileTypeDTO fileType = findFileTypeByName.execute(type.getFileType());
        
        if(!fileType.exist()) {
            throw UsecaseCustomException.CreateUserException(Messages.CreateFileUsecaseImpl.TECHNICAL_PROBLEM_CREATE_FINDFILETYPE);
        }
        return type;
        
        
        
        
        
    }
    
}
