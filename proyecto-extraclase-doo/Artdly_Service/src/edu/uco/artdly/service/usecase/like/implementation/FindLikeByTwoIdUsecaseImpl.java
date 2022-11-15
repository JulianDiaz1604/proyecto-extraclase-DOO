package edu.uco.artdly.service.usecase.like.implementation;

import java.util.List;
import java.util.UUID;

import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.LikeDTO;
import edu.uco.artdly.domain.UserDTO;
import edu.uco.artdly.service.usecase.like.FindLikeByTwoLikeUsecase;

public class FindLikeByTwoIdUsecaseImpl implements FindLikeByTwoLikeUsecase {

    private final DAOFactory factory;
    
    public FindLikeByTwoIdUsecaseImpl(DAOFactory factory) {
        this.factory=factory;
    }


    @Override
    public LikeDTO execute(UUID userid, UUID artworkid) {
        
        LikeDTO result = new LikeDTO();
        final UserDTO user = UserDTO.create(userid);
         final ArtworkDTO artwork = ArtworkDTO.create(artworkid);
        final LikeDTO like = LikeDTO.create(user, artwork);
        final List<LikeDTO> results = factory.getLikeDAO().find(like);

        if(!results.isEmpty()){
            result = results.get(0);
        }

        return result;

    }

}
