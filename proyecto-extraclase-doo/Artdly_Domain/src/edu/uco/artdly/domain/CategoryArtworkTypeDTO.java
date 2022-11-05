package domain;

import java.util.UUID;

public class CategoryArtworkTypeDTO {
    
    private UUID id;
    private ArtworkDTO artwork;
    private CategoryDTO category;

    public CategoryArtworkTypeDTO(){
        super();
    }

    public CategoryArtworkTypeDTO(final UUID id, final ArtworkDTO artwork, final CategoryDTO category){
        setId(id);
        setArtwork(artwork);
        setCategory(category);
    }

    public static final CategoryArtworkTypeDTO create(final UUID id, final ArtworkDTO artwork, final CategoryDTO category){
        return new CategoryArtworkTypeDTO(id, artwork, category);
    }

    //TO-DO: public static final CategoryArtworkTypeDTO create(all parameters, use id as String) 

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
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

}
