package edu.uco.artdly.service.usecase.file.implementation;

import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.domain.FileDTO;
import edu.uco.artdly.service.usecase.artworktype.FindArtworkTypeById;
import edu.uco.artdly.service.usecase.artworktype.implementation.FindArtworkTypeByIdImpl;
import edu.uco.artdly.service.usecase.file.CreateFileUseCase;

public class CreateFileUseCaseImpl implements CreateFileUseCase {

    private final DAOFactory factory;
    private final String folderName = "artworks";

    public CreateFileUseCaseImpl(DAOFactory factory){
        this.factory = factory;
    }

    @Override
    public void execute(FileDTO file) {
        // crear nombre de la ruta
        String pathName = createPathFile(file.getPathFile());
        // Validar si existe el tipo de archivo
    }

    private String createPathFile(String artworkTittle){
        return folderName + "/" + artworkTittle;
    }
/*
    private ArtworkTypeDTO findArtworkType(UUID id){
        final ArtworkTypeDTO user = findUserById.execute(id);

        if(user.notExist()){ //TODO create message
            throw UsecaseCustomException.CreateUserException("No existe el usuario");
        }

        return user;
    }*/
    
}
