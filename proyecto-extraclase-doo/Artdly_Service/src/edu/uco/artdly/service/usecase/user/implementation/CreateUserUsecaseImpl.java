package edu.uco.artdly.service.usecase.user.implementation;

import java.sql.Date;
import java.util.UUID;

import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.artdly.crosscutting.helper.MailHelper.trueMailForm;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.helper.StringHelper;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.UserDTO;
import edu.uco.artdly.service.usecase.user.CreateUserUsecase;
import edu.uco.artdly.service.usecase.user.FindUserByUsernameUsecase;

public class CreateUserUsecaseImpl implements CreateUserUsecase{
    
    private final DAOFactory factory;
    private final FindUserByUsernameUsecase findUserByUsername;
    
    public CreateUserUsecaseImpl(DAOFactory factory){
        this.factory = factory;
        this.findUserByUsername = new FindUserByUsernameUsecaseImpl(factory);
    }

    

    @Override
    public void execute(UserDTO user) {
        try {
            final UUID id = getNewUUID();
            final String name = validateName(user.getName()); 
            final String lastname = validateLastname(user.getLastName());
            final String mail = validateMail(user.getMail());
            final String username = validateUsername(user.getUsername());
            final String password = validatePassword(user.getPassword());
            final Date birthDate = user.getBirthDate();
            final String description = validateDescription(user.getDescription());
            final boolean isPrivate = false;
            
            user.setId(id);
            user.setName(name);
            user.setLastName(lastname);
            user.setMail(mail);
            user.setUsername(username);
            user.setPassword(password);
            user.setBirthDate(birthDate);
            user.setDescription(description);
            user.setPrivate(isPrivate);
            
            factory.getUserDAO().create(user);
            
        }catch(UsecaseCustomException exception) {
            throw exception;
        } catch(ArtdlyCustomException exception) {
            throw exception;
        } catch(Exception exception) {
            throw exception;
        }
    }
    
    private final String validateName(String name){
        if(StringHelper.isDefaultString(name)){ //TODO: create message
            throw UsecaseCustomException.CreateUserException("El nombre no puede estar vacio");
        }
        return name;
    }
    private final String validateLastname(String lastname){
        if(StringHelper.isDefaultString(lastname)){ //TODO: create message
            throw UsecaseCustomException.CreateUserException("El segundo nombre no puede estar vacio");
        }
        return lastname;
    }
    
    private final String validateMail(String mail){

        if(!trueMailForm(mail)){ //TODO: create message
            throw UsecaseCustomException.CreateUserException("NO TIENE LA FORMA DE UN CORREO");
      
    }
        return mail;
    }

    private final String validateUsername(String username){
        final UserDTO user = findUserByUsername.execute(username);
        if(invalidUsername(username)) {
            throw UsecaseCustomException.CreateUserException("que no se pueda colocar ese nick");
        }
        if(StringHelper.isDefaultString(username)){ //TODO: create message
            throw UsecaseCustomException.CreateUserException("El nickname no puede estar vacio");
        }
        if(user.exist()) {
            throw UsecaseCustomException.CreateUserException("ESTE NICK YA EXISTE");
        }
        return username;
    }
    private final String validatePassword(String password){

        if(StringHelper.isDefaultString(password)){ //TODO: create message
            throw UsecaseCustomException.CreateUserException("la contraseña no puede estar vacia");
        }
        return password;
    }

    private final String validateDescription(String description){
        if(StringHelper.isDefaultString(description)){ //TODO: create message
            return description = " ";
        }
        return description;
    }

    
    private final static boolean invalidUsername(String username) {
        String ofensive = "nigga";
        if(username.equals(ofensive)) {
            return true;
        }else {
            return false;
        }
    }
}
