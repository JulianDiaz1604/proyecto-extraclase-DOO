package edu.uco.artdly.domain;

import java.util.UUID;

public class CategoryDTO {
    
    private UUID id;
    private String name;
    private String description;

    public CategoryDTO(){
        super();
    }

    public CategoryDTO(final UUID id, final String name, final String description){
        setId(id);
        setName(name);
        setDescription(description);
    }

    public static final CategoryDTO create(final UUID id, final String name, final String description){
        return new CategoryDTO(id, name, description);
    }

    //TO-DO: public static final CategoryDTO create(all parameters, use id as String) 

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
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

}
