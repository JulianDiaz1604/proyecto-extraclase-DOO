package edu.uco.artdly.service.usecase.fileType;

import edu.uco.artdly.domain.FileTypeDTO;

public interface FindFileTypeByNameUsecase {
    FileTypeDTO execute(String name);
}
