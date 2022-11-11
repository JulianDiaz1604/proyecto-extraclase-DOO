package edu.uco.artdly.service.usecase.file.implementation;

import edu.uco.artdly.service.usecase.file.CreatePathFileUsecase;

public class CreatePathFileUsecaseImpl implements CreatePathFileUsecase {

    private static final String FOLDER_NAME = "artworks";

    public CreatePathFileUsecaseImpl(){
        super();
    }

    @Override
    public String execute(String artworkTittle) {
        return FOLDER_NAME + "/" + artworkTittle;
    }
    
}
