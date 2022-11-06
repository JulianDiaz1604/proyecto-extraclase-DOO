package edu.uco.artdly.domain;

import java.util.Date;
import java.util.UUID;

import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getDefaultUUID;

public class ArtworkDTO {
    
    private UUID id;
    private String tittle;
    private String description;
    private Date publicationDate;
    private FileDTO file;
    private ArtworkTypeDTO artworkType;
    private UserDTO user;

    public ArtworkDTO(){
        setId(getNewUUID());
    }

    public ArtworkDTO(final UUID id, final String tittle, final String description, final Date publicationDate, 
                        final FileDTO file, final ArtworkTypeDTO artworkType, final UserDTO user){
        setId(id);                    
        setTittle(tittle);
        setDescription(description);
        setPublicationDate(publicationDate);
        setFile(file);
        setArtworkType(artworkType);
        setUser(user);
    }

    public static final ArtworkDTO create(final UUID id, final String tittle, final String description, final Date publicationDate, 
    final FileDTO file, final ArtworkTypeDTO artworkType, final UserDTO user){
        return new ArtworkDTO(id, tittle, description, publicationDate, file, artworkType, user);
    }

    public static final ArtworkDTO create(final String id, final String tittle, final String description, final Date publicationDate, 
    final FileDTO file, final ArtworkTypeDTO artworkType, final UserDTO user){
        return new ArtworkDTO(getUUIDFromString(id), tittle, description, publicationDate, file, artworkType, user);
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = getDefaultUUID(id);
    }
    public String getTittle() {
        return tittle;
    }
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Date getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
    public FileDTO getFile() {
        return file;
    }
    public void setFile(FileDTO file) {
        this.file = file;
    }
    public ArtworkTypeDTO getArtworkType() {
        return artworkType;
    }
    public void setArtworkType(ArtworkTypeDTO artworkType) {
        this.artworkType = artworkType;
    }
    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }
    public final String getIdAsString() {
        return getUUIDAsString(getId());
    }

}
