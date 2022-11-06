package edu.uco.artdly.domain;

import java.util.UUID;

import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getDefaultUUID;

public class CategoryArtworkDTO {
    
    private UUID id;
    private ArtworkDTO artwork;
    private CategoryDTO category;

    public CategoryArtworkDTO(){
        setId(getNewUUID());
    }

    public CategoryArtworkDTO(final UUID id, final ArtworkDTO artwork, final CategoryDTO category){
        setId(id);
        setArtwork(artwork);
        setCategory(category);
    }

    public static final CategoryArtworkDTO create(final UUID id, final ArtworkDTO artwork, final CategoryDTO category){
        return new CategoryArtworkDTO(id, artwork, category);
    }

    public static final CategoryArtworkDTO create(final String id, final ArtworkDTO artwork, final CategoryDTO category){
        return new CategoryArtworkDTO(getUUIDFromString(id), artwork, category);
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = getDefaultUUID(id);
    }
    public ArtworkDTO getArtwork() {
        return artwork;
    }
    public void setArtwork(ArtworkDTO artwork) {
        this.artwork = artwork;
    }
    public CategoryDTO getCategory() {
        return category;
    }
    public void setCategory(CategoryDTO category) {
        this.category = category;
    }
    public final String getIdAsString() {
        return getUUIDAsString(getId());
    }

}
