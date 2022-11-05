package edu.uco.artdly.domain;

import java.util.Date;
import java.util.UUID;

public class UserDTO {
    
    private UUID id;
    private String name;
    private String lastName;
    private String mail;
    private String username;
    private String password;
    private Date birthDate;
    private String description;
    private boolean isPrivate;

    public UserDTO(){
        super();
    }

    public UserDTO(final UUID id, final String name, final String lastName, final String mail, final String username,
                    final String password, final Date birthDate, final String description, final boolean isPrivate){
        setId(id);
        setName(name);
        setLastName(lastName);
        setMail(mail);
        setUsername(username);
        setPassword(password);
        setBirthDate(birthDate);
        setDescription(description);
        setPrivate(isPrivate);
    }

    public static final UserDTO create(final UUID id, final String name, final String lastName, final String mail, final String username,
    final String password, final Date birthDate, final String description, final boolean isPrivate){
        return new UserDTO(id, name, lastName, mail, username, password, birthDate, description, isPrivate);
    }

    //TO-DO: public static final UserDTO create(all parameters, use id as String) 

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    

}
