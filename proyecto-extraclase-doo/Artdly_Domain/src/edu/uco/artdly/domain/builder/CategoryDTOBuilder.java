package edu.uco.artdly.domain.builder;

import java.util.UUID;

import edu.uco.artdly.domain.CategoryDTO;

public class CategoryDTOBuilder {
    
    private UUID id;
    private String name;
    private String description;

    public CategoryDTOBuilder(){
        super();
    }

    public final CategoryDTO build(){
        return CategoryDTO.create(id, name, description);
    }

    public final CategoryDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public final CategoryDTOBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public final CategoryDTOBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

}
