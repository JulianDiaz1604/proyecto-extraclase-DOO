package edu.uco.artdly.domain.builder;

import java.util.UUID;

import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.CategoryArtworkDTO;
import edu.uco.artdly.domain.CategoryDTO;

public class CategoryArtworkTypeDTOBuilder {
    
    private UUID id;
    private ArtworkDTO artwork;
    private CategoryDTO category;

    public CategoryArtworkTypeDTOBuilder(){
        super();
    }

    public CategoryArtworkDTO build(){
        return CategoryArtworkDTO.create(id, artwork, category);
    }

    public final CategoryArtworkTypeDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public final CategoryArtworkTypeDTOBuilder setArtwork(ArtworkDTO artwork) {
        this.artwork = artwork;
        return this;
    }

    public final CategoryArtworkTypeDTOBuilder setCategory(CategoryDTO category) {
        this.category = category;
        return this;
    }

}
