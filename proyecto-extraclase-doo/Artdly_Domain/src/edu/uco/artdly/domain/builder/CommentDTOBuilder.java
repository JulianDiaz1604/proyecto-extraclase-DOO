package edu.uco.artdly.domain.builder;

import java.util.Date;
import java.util.UUID;

import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.CommentDTO;
import edu.uco.artdly.domain.UserDTO;

public class CommentDTOBuilder {
    
    private UUID id;
    private Date realizationDate;
    private String description;
    private UserDTO user;
    private ArtworkDTO artwork;

    public CommentDTOBuilder(){
        super();
    }

    public final CommentDTO build(){
        return CommentDTO.create(id, realizationDate, description, user, artwork);
    }

    public final CommentDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public final CommentDTOBuilder setRealizationDate(Date realizationDate) {
        this.realizationDate = realizationDate;
        return this;
    }

    public final CommentDTOBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public final CommentDTOBuilder setUser(UserDTO user) {
        this.user = user;
        return this;
    }

    public final CommentDTOBuilder setArtwork(ArtworkDTO artwork) {
        this.artwork = artwork;
        return this;
    }

}
