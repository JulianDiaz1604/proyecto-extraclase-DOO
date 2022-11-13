package edu.uco.artdly.service.usecase.like.implementation;

import java.util.List;

import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.LikeDTO;
import edu.uco.artdly.domain.UserDTO;
import edu.uco.artdly.service.usecase.like.FindLikeByUserUsecase;

public class FindLikeByUserUsecaseImpl implements FindLikeByUserUsecase {
    private final DAOFactory factory;
    
    public FindLikeByUserUsecaseImpl (DAOFactory factory) {
        this.factory = factory;
    }

    @Override
    public LikeDTO execute(UserDTO user, ArtworkDTO artwork) {
        
        LikeDTO result = new LikeDTO();
        final LikeDTO like = LikeDTO.create(user,artwork);
        final List<LikeDTO> results = factory.getLikeDAO().find(like);

        if(!results.isEmpty()){
            result = results.get(0);
        }

        return result;
    }

}
