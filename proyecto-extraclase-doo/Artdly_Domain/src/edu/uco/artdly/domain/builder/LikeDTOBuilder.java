package edu.uco.artdly.domain.builder;

import java.sql.Date;
import java.util.UUID;

import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.LikeDTO;
import edu.uco.artdly.domain.UserDTO;

public class LikeDTOBuilder {
    
    private UUID id;
    private Date realizationDate;
    private UserDTO user;
    private ArtworkDTO artwork;

    public LikeDTOBuilder(){
        super();
    }

    public LikeDTO build(){
        return LikeDTO.create(id, realizationDate, user, artwork);
    }

    public final LikeDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public final LikeDTOBuilder setRealizationDate(Date realizationDate) {
        this.realizationDate = realizationDate;
        return this;
    }

    public final LikeDTOBuilder setUser(UserDTO user) {
        this.user = user;
        return this;
    }

    public final LikeDTOBuilder setArtwork(ArtworkDTO artwork) {
        this.artwork = artwork;
        return this;
    }

}
