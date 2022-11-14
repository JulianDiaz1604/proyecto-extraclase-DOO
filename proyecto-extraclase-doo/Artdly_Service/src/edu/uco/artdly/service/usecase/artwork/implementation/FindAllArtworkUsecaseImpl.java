package edu.uco.artdly.service.usecase.artwork.implementation;

import java.util.List;

import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.service.usecase.artwork.FindAllArtworkUsecase;

public class FindAllArtworkUsecaseImpl implements FindAllArtworkUsecase{
    private  DAOFactory factory;
    
    public FindAllArtworkUsecaseImpl(DAOFactory factory) {
        this.factory = factory;
    }
    
    

    
    @Override
    public List<ArtworkDTO> execute(){
        return  factory.getArtworkDAO().findAll();
    }

}
