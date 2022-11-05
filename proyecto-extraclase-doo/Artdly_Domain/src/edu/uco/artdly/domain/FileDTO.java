package edu.uco.artdly.domain;

import java.util.UUID;

public class FileDTO {
    
    private UUID id;
    private String pathFile;
    private String typeFile;

    public FileDTO(){
        super();
    }

    public FileDTO(final UUID id, final String pathFile, final String typeFile){
        setId(id);
        setPathFile(pathFile);
        setTypeFile(typeFile);
    }

    public static final FileDTO create(final UUID id, final String pathFile, final String typeFile){
        return new FileDTO(id, pathFile, typeFile);
    }

    //TO-DO: public static final FileDTO create(all parameters, use id as String) 

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }
    public String getPathFile() {
        return pathFile;
    }
    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }
    public String getTypeFile() {
        return typeFile;
    }
    public void setTypeFile(String typeFile) {
        this.typeFile = typeFile;
    }

}
