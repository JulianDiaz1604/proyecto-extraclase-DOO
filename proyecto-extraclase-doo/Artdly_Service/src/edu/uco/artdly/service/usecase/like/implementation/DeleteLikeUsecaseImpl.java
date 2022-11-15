package edu.uco.artdly.service.usecase.like.implementation;



import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.service.usecase.like.DeleteLikeUsecase;



public class DeleteLikeUsecaseImpl implements DeleteLikeUsecase {
    
    
    private final DAOFactory factory;
    
    public DeleteLikeUsecaseImpl(DAOFactory factory){
        this.factory = factory;
    }
    
    @Override
    public void execute(UUID id) {
        try {
            factory.getLikeDAO().delete(id);
            
                                       
        }catch(UsecaseCustomException exception) {
            throw exception;
        } catch(ArtdlyCustomException exception) {
            throw UsecaseCustomException.wrapException(null, exception);
        } catch(Exception exception) {
            throw UsecaseCustomException.CreateBusinessException(null, exception);
        }
    } 
  
}

     