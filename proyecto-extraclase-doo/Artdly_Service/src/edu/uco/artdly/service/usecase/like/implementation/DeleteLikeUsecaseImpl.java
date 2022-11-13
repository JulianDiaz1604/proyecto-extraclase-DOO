package edu.uco.artdly.service.usecase.like.implementation;



import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;

import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.LikeDTO;
import edu.uco.artdly.domain.UserDTO;
import edu.uco.artdly.service.usecase.like.DeleteLikeUsecase;
import edu.uco.artdly.service.usecase.like.FindLikeByUserUsecase;

public class DeleteLikeUsecaseImpl implements DeleteLikeUsecase {
    
    
    private final DAOFactory factory;
    private final FindLikeByUserUsecase findLikeByUserUsecase;
    public DeleteLikeUsecaseImpl(DAOFactory factory){
        this.factory = factory;
        this.findLikeByUserUsecase = new FindLikeByUserUsecaseImpl(factory);
    }
    
    public void execute(LikeDTO like) {
        try {
            if(existLike(like.getUser(),like.getArtwork())) {
                
                
                LikeDTO newLike = findLikeByUserUsecase.execute(like.getUser(), like.getArtwork());
                
                
                factory.getLikeDAO().delete(newLike.getId()); 
            }
           
        
        }catch(UsecaseCustomException exception) {
            throw exception;
        } catch(ArtdlyCustomException exception) {
            throw exception;
        } catch(Exception exception) {
            throw exception;
        }
    } 
    private final boolean existLike(UserDTO user, ArtworkDTO artwork){
        final LikeDTO like = findLikeByUserUsecase.execute(user, artwork);
        if(like.exist()){ 
            return true;
            
        }
        return false;
    }
}

     