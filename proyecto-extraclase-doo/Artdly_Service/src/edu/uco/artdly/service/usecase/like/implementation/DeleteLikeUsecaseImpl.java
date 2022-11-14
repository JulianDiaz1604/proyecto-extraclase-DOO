package edu.uco.artdly.service.usecase.like.implementation;



import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.LikeDTO;
import edu.uco.artdly.service.usecase.like.DeleteLikeUsecase;
import edu.uco.artdly.service.usecase.like.FindLikeByIdUsecase;


public class DeleteLikeUsecaseImpl implements DeleteLikeUsecase {
    
    
    private final DAOFactory factory;
    private final FindLikeByIdUsecase findLikeUsecase;
    public DeleteLikeUsecaseImpl(DAOFactory factory){
        this.factory = factory;
        this.findLikeUsecase = new FindLikeByIdUsecaseImpl(factory);
    }
    
    @Override
    public void execute(UUID id) {
        try {
            LikeDTO like = findLikeUsecase.execute(id);
            factory.getLikeDAO().delete(id);
            
                                       
        }catch(UsecaseCustomException exception) {
            throw exception;
        } catch(ArtdlyCustomException exception) {
            throw exception;
        } catch(Exception exception) {
            throw exception;
        }
    } 
  
}

     