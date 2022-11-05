package edu.uco.artdly.domain.builder;

import java.util.Date;
import java.util.UUID;

import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.domain.FileDTO;
import edu.uco.artdly.domain.UserDTO;

public class ArtworkDTOBuilder {
    
    private UUID id;
    private String tittle;
    private String description;
    private Date publicationDate;
    private FileDTO file;
    private ArtworkTypeDTO artworkType;
    private UserDTO user;

    public ArtworkDTOBuilder(){
        super();
    }

    public final ArtworkDTO create(){
        return ArtworkDTO.create(id, tittle, description, publicationDate, file, artworkType, user);
    }

    public final ArtworkDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public final ArtworkDTOBuilder setTittle(String tittle) {
        this.tittle = tittle;
        return this;
    }

    public final ArtworkDTOBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public final ArtworkDTOBuilder setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
        return this;
    }

    public final ArtworkDTOBuilder setFile(FileDTO file) {
        this.file = file;
        return this;
    }

    public final ArtworkDTOBuilder setArtworkType(ArtworkTypeDTO artworkType) {
        this.artworkType = artworkType;
        return this;
    }

    public final ArtworkDTOBuilder setUser(UserDTO user) {
        this.user = user;
        return this;
    }

}
