package edu.uco.artdly.domain;

import java.util.UUID;

public class ArtworkTypeDTO {
    
    private UUID id;
    private String name;

    public ArtworkTypeDTO(){
        super();
    }

    public ArtworkTypeDTO(final UUID id, final String name){
        setId(id);
        setName(name);
    }

    public static final ArtworkTypeDTO create(final UUID id, final String name){
        return new ArtworkTypeDTO(id, name);
    }

    //TO-DO: public static final ArtworkTypeDTO create(all parameters, use id as String) 

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

}
