package edu.uco.artdly.service.usecase.artworktype.implementation;

import java.util.List;

import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.service.usecase.artworktype.FindAllArtworkTypeUsecase;

public class FindAllArtworkTypesUsecaseImpl implements FindAllArtworkTypeUsecase {
    
    private DAOFactory factory;

    public void FindAllArtworkTypesUsecase(){
        
    }
        

    @Override
    public List<ArtworkTypeDTO> exexute() {
        final List<ArtworkTypeDTO> artworkType = factory.getArtworkTypeDAO().findAll();


       return artworkType;

   }  
}


