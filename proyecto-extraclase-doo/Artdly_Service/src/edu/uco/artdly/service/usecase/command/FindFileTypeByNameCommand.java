package edu.uco.artdly.service.usecase.command;


import edu.uco.artdly.domain.FileTypeDTO;

public interface FindFileTypeByNameCommand {
    FileTypeDTO execute(String name);

}
