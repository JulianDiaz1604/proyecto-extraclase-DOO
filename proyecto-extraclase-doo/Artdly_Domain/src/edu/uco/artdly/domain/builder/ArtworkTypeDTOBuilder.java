package edu.uco.artdly.domain.builder;

import java.util.UUID;

import edu.uco.artdly.domain.ArtworkTypeDTO;

public class ArtworkTypeDTOBuilder {
    
    private UUID id;
    private String name;

    public ArtworkTypeDTOBuilder(){
        super();
    }

    public final ArtworkTypeDTO build(){
        return ArtworkTypeDTO.create(id, name);
    }

    public final ArtworkTypeDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public final ArtworkTypeDTOBuilder setName(String name) {
        this.name = name;
        return this;
    }

}
