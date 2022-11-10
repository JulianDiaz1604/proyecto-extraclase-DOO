package edu.uco.artdly.service.usecase.artwork.implementation;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.helper.StringHelper;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.service.usecase.artwork.CreateArtworkUsecase;
import edu.uco.artdly.service.usecase.artworktype.FindArtworkTypeById;
import edu.uco.artdly.service.usecase.artworktype.implementation.FindArtworkTypeByIdImpl;

public class CreateArtworkUsecaseImpl implements CreateArtworkUsecase {

    private final DAOFactory factory;
    private final FindArtworkTypeById findArtworkTypeById;

    public CreateArtworkUsecaseImpl(DAOFactory factory){
        this.factory = factory;
        findArtworkTypeById = new FindArtworkTypeByIdImpl(factory);
    }

    @Override
    public void execute(ArtworkDTO artwork) {
        //Verificar que el titulo no esté vacio
        if(StringHelper.isDefaultString(artwork.getTittle())){ //TODO: create message
            throw UsecaseCustomException.CreateUserException("El titulo de la obra no puede estar vacio");
        }
        //Establecer fecha de publicacion
        Date date = new Date();
        //Verificar que File exista
        //Verificar que exista el tipo de obra
        final ArtworkTypeDTO artworkType = findArtworkTypeById.execute(artwork.getArtworkType().getId());
        //Verificar que exista el usuario
    }
    
}
