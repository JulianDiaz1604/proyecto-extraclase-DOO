package edu.uco.artdly.domain;

import java.util.Date;
import java.util.UUID;

import static edu.uco.artdly.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.artdly.crosscutting.helper.DateHelper.getDeafultDate;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getDefaultUUID;

public class CommentDTO {
    
    private UUID id;
    private Date realizationDate;
    private String description;
    private UserDTO user;
    private ArtworkDTO artwork;

    public CommentDTO(){
        setId(getDefaultUUID(id));
        setRealizationDate(getDeafultDate());
        setDescription(EMPTY);
        setUser(new UserDTO());
        setArtwork(new ArtworkDTO());
        
    }

    public CommentDTO(final UUID id, final Date realizationDate, final String description, final UserDTO user, 
                        final ArtworkDTO artwork){
        setId(id);
        setRealizationDate(realizationDate);
        setDescription(description);
        setUser(user);
        setArtwork(artwork);
    }

    public static final CommentDTO create(final UUID id, final Date realizationDate, final String description, 
                                            final UserDTO user, final ArtworkDTO artwork){
        return new CommentDTO(id, realizationDate, description, user, artwork);
    }
    public static final CommentDTO create(final UUID id){
return new CommentDTO(id, getDeafultDate(), EMPTY, new UserDTO(), new ArtworkDTO());
}

    public static final CommentDTO create(final String id, final Date realizationDate, final String description, 
                                            final UserDTO user, final ArtworkDTO artwork){
        return new CommentDTO(getUUIDFromString(id), realizationDate, description, user, artwork);
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = getDefaultUUID(id);
    }
    public Date getRealizationDate() {
        return realizationDate;
    }
    public void setRealizationDate(Date realizationDate) {
        this.realizationDate = realizationDate;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user) {
        this.user = user;
    }
    public ArtworkDTO getArtwork() {
        return artwork;
    }
    public void setArtwork(ArtworkDTO artwork) {
        this.artwork = artwork;
    }
    public final String getIdAsString() {
        return getUUIDAsString(getId());
    }

}
