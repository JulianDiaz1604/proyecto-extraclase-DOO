package edu.uco.artdly.service.usecase.fileType;

import java.util.List;

import edu.uco.artdly.domain.FileTypeDTO;

public interface FindAllFileTypeUsecase {
    List<FileTypeDTO> execute();

}
