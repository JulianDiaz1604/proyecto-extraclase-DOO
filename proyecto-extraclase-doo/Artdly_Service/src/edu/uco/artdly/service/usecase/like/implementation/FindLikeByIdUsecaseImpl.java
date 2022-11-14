package edu.uco.artdly.service.usecase.like.implementation;

import java.util.List;
import java.util.UUID;

import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.LikeDTO;
import edu.uco.artdly.service.usecase.like.FindLikeByIdUsecase;


public class FindLikeByIdUsecaseImpl implements FindLikeByIdUsecase{
    private final DAOFactory factory;

    public FindLikeByIdUsecaseImpl(DAOFactory factory){
        this.factory = factory;
    }

    @Override
    public LikeDTO execute(UUID id) {
        
        LikeDTO result = new LikeDTO();
        final LikeDTO like = LikeDTO.create(id);
        final List<LikeDTO> results = factory.getLikeDAO().find(like);

        if(!results.isEmpty()){
            result = results.get(0);
        }

        return result;

    }

}