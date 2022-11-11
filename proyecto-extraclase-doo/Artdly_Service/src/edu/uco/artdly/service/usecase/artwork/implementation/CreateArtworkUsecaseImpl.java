package edu.uco.artdly.service.usecase.artwork.implementation;

import java.util.Date;
import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.helper.StringHelper;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.domain.UserDTO;
import edu.uco.artdly.service.usecase.artwork.CreateArtworkUsecase;
import edu.uco.artdly.service.usecase.artworktype.FindArtworkTypeById;
import edu.uco.artdly.service.usecase.artworktype.implementation.FindArtworkTypeByIdImpl;
import edu.uco.artdly.service.usecase.user.FindUserById;
import edu.uco.artdly.service.usecase.user.implementation.FindUserByIdImpl;

public class CreateArtworkUsecaseImpl implements CreateArtworkUsecase {

    private final DAOFactory factory;
    private final FindArtworkTypeById findArtworkTypeById;
    private final FindUserById findUserById;

    public CreateArtworkUsecaseImpl(DAOFactory factory){
        this.factory = factory;
        findArtworkTypeById = new FindArtworkTypeByIdImpl(factory);
        findUserById = new FindUserByIdImpl(factory);
    }

    @Override
    public void execute(ArtworkDTO artwork) {
        try {
            //Verificar que el titulo no esté vacio
            if(StringHelper.isDefaultString(artwork.getTittle())){ //TODO: create message
                throw UsecaseCustomException.CreateUserException("El titulo de la obra no puede estar vacio");
            }
            //Establecer fecha de publicacion
            Date date = new Date();
            //Crear File
            final ArtworkTypeDTO artworkType = findArtworkType(artwork.getArtworkType().getId());
            final UserDTO user = findUser(artwork.getUser().getId());

            factory.getArtworkDAO().create(artwork);

        } catch(UsecaseCustomException exception) {
            throw exception;
        } catch(ArtdlyCustomException exception) {
            throw exception;
        } catch(Exception exception) {
            //Excepcion customizada
            throw exception;
        }
    }

    private final ArtworkTypeDTO findArtworkType(final UUID id){
        final ArtworkTypeDTO artworkType = findArtworkTypeById.execute(id);

        if(artworkType.notExist()){ //TODO: create message
            throw UsecaseCustomException.CreateUserException("No existe el tipo de obra seleccionado");
        }

        return artworkType;
    }

    private final UserDTO findUser(final UUID id){
        final UserDTO user = findUserById.execute(id);

        if(user.notExist()){ //TODO create message
            throw UsecaseCustomException.CreateUserException("No existe el usuario");
        }

        return user;
    }
    
}
