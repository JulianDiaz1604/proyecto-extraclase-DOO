package edu.uco.artdly.domain.builder;

import java.sql.Date;
import java.util.UUID;

import edu.uco.artdly.domain.UserDTO;

public class UserDTOBuilder {
    
    private UUID id;
    private String name;
    private String lastName;
    private String mail;
    private String username;
    private String password;
    private Date birthDate;
    private String description;
    private boolean isPrivate;

    public UserDTOBuilder(){
        super();
    }

    public final UserDTOBuilder getUserDTOBuilder(){
        return new UserDTOBuilder();
    }

    public final UserDTO build(){
        return UserDTO.create(id, name, lastName, mail, username, password, birthDate, description, isPrivate);
    }

    public final UserDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }
    public final UserDTOBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public final UserDTOBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
    public final UserDTOBuilder setMail(String mail) {
        this.mail = mail;
        return this;
    }
    public final UserDTOBuilder setUsername(String username) {
        this.username = username;
        return this;
    }
    public final UserDTOBuilder setPassword(String password) {
        this.password = password;
        return this;
    }
    public final UserDTOBuilder setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }
    public final UserDTOBuilder setDescription(String description) {
        this.description = description;
        return this;
    }
    public final UserDTOBuilder setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
        return this;
    }

}
