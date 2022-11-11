package edu.uco.artdly.service.usecase.file;

import edu.uco.artdly.domain.FileDTO;

public interface CreateFileUsecase {
    FileDTO execute(FileDTO file);
}
