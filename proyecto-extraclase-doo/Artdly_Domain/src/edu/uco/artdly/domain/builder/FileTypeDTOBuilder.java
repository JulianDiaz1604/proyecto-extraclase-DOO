package edu.uco.artdly.domain.builder;

import java.util.UUID;

import edu.uco.artdly.domain.FileTypeDTO;

public class FileTypeDTOBuilder {
    
    private UUID id;
    private String typeFile;

    public FileTypeDTOBuilder(){
        super();
    }

    public FileTypeDTO build(){
        return FileTypeDTO.create(id, typeFile);
    }

    public final FileTypeDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public final FileTypeDTOBuilder setTypeFile(String typeFile) {
        this.typeFile = typeFile;
        return this;
    }
    
}
