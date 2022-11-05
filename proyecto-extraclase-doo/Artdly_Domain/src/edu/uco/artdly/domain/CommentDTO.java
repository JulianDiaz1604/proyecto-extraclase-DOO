package edu.uco.artdly.domain;

import java.util.Date;
import java.util.UUID;

public class CommentDTO {
    
    private UUID id;
    private Date realizationDate;
    private String description;
    private UserDTO user;
    private ArtworkDTO artwork;

    public CommentDTO(){
        super();
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

    //TO-DO: public static final CommentDTO create(all parameters, use id as String) 

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
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

}
