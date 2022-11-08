package edu.uco.artdly.domain;

import java.util.UUID;

import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getNewUUID;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getDefaultUUID;

public class FileDTO {
    
    private UUID id;
    private String pathFile;
    private FileTypeDTO typeFile;

    public FileDTO(){
        setId(getNewUUID());
    }

    public FileDTO(final UUID id, final String pathFile, final FileTypeDTO typeFile){
        setId(id);
        setPathFile(pathFile);
        setTypeFile(typeFile);
    }

    public static final FileDTO create(final UUID id, final String pathFile, final FileTypeDTO typeFile){
        return new FileDTO(id, pathFile, typeFile);
    }

    public static final FileDTO create(final String id, final String pathFile, final FileTypeDTO typeFile){
        return new FileDTO(getUUIDFromString(id), pathFile, typeFile);
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = getDefaultUUID(id);
    }
    public String getPathFile() {
        return pathFile;
    }
    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }
    public FileTypeDTO getTypeFile() {
        return typeFile;
    }
    public void setTypeFile(FileTypeDTO typeFile) {
        this.typeFile = typeFile;
    }
    public final String getIdAsString() {
        return getUUIDAsString(getId());
    }

}
