package edu.uco.artdly.domain;

import java.sql.Date;
import java.util.UUID;

import edu.uco.artdly.crosscutting.helper.UUIDHelper;

import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.artdly.crosscutting.helper.DateHelper.getDeafultDate;


public class LikeDTO {
    
    private UUID id;
    private Date realizationDate;
    private UserDTO user;
    private ArtworkDTO artwork;

    public LikeDTO(){
        setId(getDefaultUUID(id));
        setRealizationDate(getDeafultDate());
        setUser(new UserDTO());
        setArtwork(new ArtworkDTO());
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
    public static final LikeDTO create(final UUID id) {
        return new LikeDTO(id, getDeafultDate(), new UserDTO(), new ArtworkDTO());
    }

    public static final LikeDTO create(final String id, final Date realizationDate, final UserDTO user, final ArtworkDTO artwork) {
        return new LikeDTO(getUUIDFromString(id), realizationDate, user, artwork);
    }
    public static final LikeDTO create (final UserDTO user, final ArtworkDTO artwork) {
        return new LikeDTO (UUIDHelper.getDefaultUUID(), null, user, artwork);
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
    public boolean exist() {
        return !UUIDHelper.isDefaultUUID(id);
    }
    public boolean notExist() {
        return !exist();
    }


}
