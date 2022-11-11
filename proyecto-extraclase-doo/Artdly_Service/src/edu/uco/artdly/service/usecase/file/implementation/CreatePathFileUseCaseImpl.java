package edu.uco.artdly.service.usecase.file.implementation;

import edu.uco.artdly.service.usecase.file.CreatePathFileUseCase;

public class CreatePathFileUseCaseImpl implements CreatePathFileUseCase {

    private static final String FOLDER_NAME = "artworks";

    public CreatePathFileUseCaseImpl(){
        super();
    }

    @Override
    public String execute(String artworkTittle) {
        return FOLDER_NAME + "/" + artworkTittle;
    }
    
}
