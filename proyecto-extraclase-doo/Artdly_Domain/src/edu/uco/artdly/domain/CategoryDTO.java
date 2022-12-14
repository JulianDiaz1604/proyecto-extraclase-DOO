package edu.uco.artdly.domain;

import java.util.UUID;

import edu.uco.artdly.crosscutting.helper.UUIDHelper;

import static edu.uco.artdly.crosscutting.helper.StringHelper.EMPTY;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getDefaultUUID;

public class CategoryDTO {
    
    private UUID id;
    private String name;
    private String description;

    public CategoryDTO(){
        setId(getDefaultUUID(id));
        setName(EMPTY);
        setDescription(EMPTY);
        
    }

    public CategoryDTO(final UUID id, final String name, final String description){
        setId(id);
        setName(name);
        setDescription(description);
    }

    public static final CategoryDTO create(final UUID id, final String name, final String description){
        return new CategoryDTO(id, name, description);
    }
    
    public static final CategoryDTO create(final UUID id){
        return new CategoryDTO(id, EMPTY, EMPTY);
    }

    public static final CategoryDTO create(final String id, final String name, final String description){
        return new CategoryDTO(getUUIDFromString(id), name, description);
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
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
