package edu.uco.artdly.domain;

import java.util.UUID;

import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getDefaultUUID;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDAsString;
import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDFromString;
import static edu.uco.artdly.crosscutting.helper.StringHelper.EMPTY;

public class FileTypeDTO {

	private UUID id;
	private String fileType;

	public FileTypeDTO(){
		setId(getDefaultUUID(id));
		setFileType(EMPTY);
	}

	public FileTypeDTO(final UUID id, final String fileType){
		setId(id);
		setFileType(fileType);
	}

	public static final FileTypeDTO create(final UUID id, final String fileType){
		return new FileTypeDTO(id, fileType);
	}
	
    public static final FileTypeDTO create(final UUID id){
        return new FileTypeDTO(id, EMPTY);
    }	

	public static final FileTypeDTO create(final String id, final String fileType){
		return new FileTypeDTO(getUUIDFromString(id), fileType);
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = getDefaultUUID(id);
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
    public final String getIdAsString() {
        return getUUIDAsString(getId());
    }

}
