package edu.uco.artdly.service.usecase.artwork.implementation;

import java.util.List;
import java.util.UUID;

import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.service.usecase.artwork.FindArtworkById;

public class FindArtworkByIdImpl implements FindArtworkById {

    private final DAOFactory factory;

    public FindArtworkByIdImpl(DAOFactory factory){
        this.factory = factory;
    }

    @Override
    public ArtworkDTO execute(UUID id) {
        
        ArtworkDTO result = new ArtworkDTO();
        final ArtworkDTO user = ArtworkDTO.create(id);
        final List<ArtworkDTO> results = factory.getArtworkDAO().find(user);

        if(!results.isEmpty()){
            result = results.get(0);
        }

        return result;

    }
    
}
