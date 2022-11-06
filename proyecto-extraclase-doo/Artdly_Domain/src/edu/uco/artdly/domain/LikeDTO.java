package edu.uco.artdly.domain;

import java.util.Date;
import java.util.UUID;

import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getDefaultUUID;

public class LikeDTO {
    
    private UUID id;
    private Date realizationDate;
    private UserDTO user;
    private ArtworkDTO artwork;

    public LikeDTO(){
        setId(getNewUUID());
    }

    public LikeDTO(final UUID id, final Date realizationDate, final UserDTO user, final ArtworkDTO artwork){
        setId(id);
        setRealizationDate(realizationDate);
        setUser(user);
        setArtwork(artwork);
    }

    public static final LikeDTO create(final UUID id, final Date realizationDate, final UserDTO user, final ArtworkDTO artwork) {
        return new LikeDTO(id, realizationDate, user, artwork);
    }

    public static final LikeDTO create(final String id, final Date realizationDate, final UserDTO user, final ArtworkDTO artwork) {
        return new LikeDTO(getUUIDFromString(id), realizationDate, user, artwork);
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
    public UserDTO getUser() {
        return user;
    }
    public void setUser(UserDTO user){
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
