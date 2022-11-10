package edu.uco.artdly.service.usecase.artwork.implementation;

import java.util.UUID;

import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.service.usecase.artwork.CreateArtworkUsecase;

public class CreateArtworkUsecaseImpl implements CreateArtworkUsecase {

    @Override
    public void execute(ArtworkDTO artwork) {
        //Verificar que el titulo no esté vacio
        //Establecer fecha de publicacion
        //Verificar que File exista
        //Verificar que exista el tipo de obra
        //Verificar que exista el usuario
    }

    private final ArtworkTypeDTO findArtworkType(final UUID id){

        final ArtworkTypeDTO artworkType = 

    }
    
}
