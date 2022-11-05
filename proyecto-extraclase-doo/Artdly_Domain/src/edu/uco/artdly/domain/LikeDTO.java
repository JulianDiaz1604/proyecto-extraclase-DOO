package domain;

import java.util.Date;
import java.util.UUID;

public class LikeDTO {
    
    private UUID id;
    private Date realizationDate;
    private UserDTO user;
    private ArtworkDTO artwork;

    public LikeDTO(){
        super();
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

    //TO-DO: public static final LikeDTO create(all parameters, use id as String) 

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

}
