package edu.uco.artdly.service.usecase.fileType;

import edu.uco.artdly.domain.FileTypeDTO;

public interface FindFileTypeByName {
    FileTypeDTO execute(String name);
}
