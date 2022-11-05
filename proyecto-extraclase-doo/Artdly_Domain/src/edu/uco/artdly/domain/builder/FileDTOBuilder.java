package edu.uco.artdly.domain.builder;

import java.util.UUID;

import edu.uco.artdly.domain.FileDTO;

public class FileDTOBuilder {
    
    private UUID id;
    private String pathFile;
    private String typeFile;

    public FileDTOBuilder(){
        super();
    }

    public FileDTO build(){
        return FileDTO.create(id, pathFile, typeFile);
    }

    public final FileDTOBuilder setId(UUID id) {
        this.id = id;
        return this;
    }

    public final FileDTOBuilder setPathFile(String pathFile) {
        this.pathFile = pathFile;
        return this;
    }

    public final FileDTOBuilder setTypeFile(String typeFile) {
        this.typeFile = typeFile;
        return this;
    }

}
