package edu.uco.artdly.domain;

import java.util.UUID;

import edu.uco.artdly.crosscutting.helper.UUIDHelper;

import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.artdly.crosscutting.helper.StringHelper.EMPTY;

public class ArtworkTypeDTO {
    
    private UUID id;
    private String name;

    public ArtworkTypeDTO(){
        setId(getDefaultUUID(id));
        setName(EMPTY);
    }

    public ArtworkTypeDTO(final UUID id, final String name){
        setId(id);
        setName(name);
    }

    public static ArtworkTypeDTO create(UUID id) {
        return new ArtworkTypeDTO(id, EMPTY);
    }

    public static final ArtworkTypeDTO create(final UUID id, final String name){
        return new ArtworkTypeDTO(id, name);
    }

    public static final ArtworkTypeDTO create(final String id, final String name){
        return new ArtworkTypeDTO(getUUIDFromString(id), name);
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = getDefaultUUID(id);
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public final String getIdAsString() {
        return getUUIDAsString(getId());
    }

}
