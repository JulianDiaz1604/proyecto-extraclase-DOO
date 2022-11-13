package edu.uco.artdly.domain;

import java.sql.Date;
import java.util.UUID;

import edu.uco.artdly.crosscutting.helper.UUIDHelper;

import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.artdly.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.artdly.crosscutting.helper.MailHelper.getDefaultMail;
import static edu.uco.artdly.crosscutting.helper.DateHelper.getDeafultDate;

public class UserDTO {
    
    private UUID id;
    private String name;
    private String lastname;
    private String mail;
    private String username;
    private String password;
    private Date birthDate;
    private String description;
    private boolean isPrivate;

    public UserDTO(){
        setId(getDefaultUUID(id));
        setName(EMPTY);
        setLastName(EMPTY);
        setMail(getDefaultMail());
        setUsername(EMPTY);
        setPassword(EMPTY);
        setBirthDate(getDeafultDate());
        setDescription(EMPTY);
        setPrivate(false);
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

    public static final UserDTO create(UUID id) {
        return new UserDTO(id, EMPTY, EMPTY, getDefaultMail(), EMPTY, EMPTY, getDeafultDate(), EMPTY, false);
    }

    public static final UserDTO create(final UUID id, final String name, final String lastName, final String mail, final String username,
    final String password, final Date birthDate, final String description, final boolean isPrivate){
        return new UserDTO(id, name, lastName, mail, username, password, birthDate, description, isPrivate);
    }
    public static final UserDTO create(final String username){
        return new UserDTO(UUIDHelper.getDefaultUUID(null), EMPTY, EMPTY, getDefaultMail(), username, EMPTY, getDeafultDate(), EMPTY, false);
    }


    public static final UserDTO create(final String id, final String name, final String lastName, final String mail, final String username,
    final String password, final Date birthDate, final String description, final boolean isPrivate){
        return new UserDTO(getUUIDFromString(id), name, lastName, mail, username, password, birthDate, description, isPrivate);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = getDefaultUUID(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastname;
    }

    public void setLastName(String lastName) {
        this.lastname = lastName;
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
    public final String getIdAsString() {
        return getUUIDAsString(getId());
    }
    public boolean exist() {
        return !UUIDHelper.isDefaultUUID(id);
    }
    public boolean notExist() {
        return !exist();
    }

    

}
