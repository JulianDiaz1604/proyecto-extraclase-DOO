package edu.uco.artdly.service.usecase.command;

import edu.uco.artdly.domain.FileDTO;

public interface CreatePathFileCommand {
    void execute(FileDTO pathFile);
}
