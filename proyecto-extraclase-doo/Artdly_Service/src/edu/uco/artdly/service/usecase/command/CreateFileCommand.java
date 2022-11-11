package edu.uco.artdly.service.usecase.command;

import edu.uco.artdly.domain.FileDTO;

public interface CreateFileCommand {
    void execute(final FileDTO file);
}
