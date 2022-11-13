package edu.uco.artdly.service.usecase.artwork.implementation;

import java.util.Date;
import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.helper.DateHelper;
import edu.uco.artdly.crosscutting.helper.StringHelper;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.domain.FileDTO;
import edu.uco.artdly.domain.UserDTO;
import edu.uco.artdly.service.usecase.artwork.CreateArtworkUsecase;
import edu.uco.artdly.service.usecase.artworktype.FindArtworkTypeByIdUsecase;
import edu.uco.artdly.service.usecase.artworktype.implementation.FindArtworkTypeByIdUsecaseImpl;
import edu.uco.artdly.service.usecase.file.CreateFileUsecase;
import edu.uco.artdly.service.usecase.file.implementation.CreateFileUsecaseImpl;
import edu.uco.artdly.service.usecase.user.FindUserByIdUsecase;
import edu.uco.artdly.service.usecase.user.implementation.FindUserByIdUsecaseImpl;

public class CreateArtworkUsecaseImpl implements CreateArtworkUsecase {

    private final DAOFactory factory;
    private final FindArtworkTypeByIdUsecase findArtworkTypeById;
    private final FindUserByIdUsecase findUserById;
    private final CreateFileUsecase createFileUseCase;

    public CreateArtworkUsecaseImpl(DAOFactory factory){
        this.factory = factory;
        findArtworkTypeById = new FindArtworkTypeByIdUsecaseImpl(factory);
        findUserById = new FindUserByIdUsecaseImpl(factory);
        createFileUseCase = new CreateFileUsecaseImpl(factory);
    }

    @Override
    public void execute(ArtworkDTO artwork) {
        try {
            final String tittle = validateTittle(artwork.getTittle());
            final Date publicationDate = getPublicationDate();
            final FileDTO file = createFileUseCase.execute(artwork.getFile());
            final ArtworkTypeDTO artworkType = findArtworkType(artwork.getArtworkType().getId());
            final UserDTO user = findUser(artwork.getUser().getId());

            artwork.setTittle(tittle);
            artwork.setPublicationDate(publicationDate);
            artwork.setFile(file);
            artwork.setArtworkType(artworkType);
            artwork.setUser(user);

            factory.getArtworkDAO().create(artwork);

        } catch(UsecaseCustomException exception) {
            throw exception;
        } catch(ArtdlyCustomException exception) {
            throw exception;
        } catch(Exception exception) {
            throw exception;
        }
    }

    private final String validateTittle(String tittle){
        if(StringHelper.isDefaultString(tittle)){ //TODO: create message
            throw UsecaseCustomException.CreateUserException("El titulo de la obra no puede estar vacio");
        }
        return tittle;
    }

    private final Date getPublicationDate(){
        return DateHelper.getNow();
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
