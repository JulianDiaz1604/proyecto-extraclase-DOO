package edu.uco.artdly.service.usecase.artworktype.implementation;

import java.util.List;
import java.util.UUID;

import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.service.usecase.artworktype.FindArtworkTypeById;

public final class FindArtworkTypeByIdImpl implements FindArtworkTypeById {

    private final DAOFactory factory;

    public FindArtworkTypeByIdImpl(DAOFactory factory){
        this.factory = factory;
    }

    @Override
    public final ArtworkTypeDTO execute(UUID id) {

        ArtworkTypeDTO result = new ArtworkTypeDTO();
        final ArtworkTypeDTO artworkType = ArtworkTypeDTO.create(id);
        final List<ArtworkTypeDTO> results = factory.getArtworkTypeDAO().find(artworkType);

        if(!results.isEmpty()){
            result = results.get(0);
        }

        return result;
        
    }

}
