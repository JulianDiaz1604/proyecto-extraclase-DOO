package edu.uco.artdly.service.usecase.like.implementation;


import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getNewUUID;

import java.sql.Date;
import java.util.UUID;

import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.exception.usecase.UsecaseCustomException;
import edu.uco.artdly.crosscutting.helper.DateHelper;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.domain.LikeDTO;
import edu.uco.artdly.domain.UserDTO;
import edu.uco.artdly.service.usecase.like.FindLikeByUserUsecase;
import edu.uco.artdly.service.usecase.like.CreateLikeUsecase;

    public class CreateLikeUsecaseImpl implements CreateLikeUsecase {
        
    
        private final DAOFactory factory;
        private final FindLikeByUserUsecase findLikeByUserUsecase;
    
        public CreateLikeUsecaseImpl(DAOFactory factory){
            this.factory = factory;
            this.findLikeByUserUsecase = new FindLikeByUserUsecaseImpl(factory);
        }

    
    @Override
    public void execute(LikeDTO like) {
       try {
           if(existLike(like.getUser(),like.getArtwork())) {
               throw UsecaseCustomException.CreateUserException(Messages.CreateLikeUsecaseImpl.TECHNICAL_PROBLEM_REGISTRE_LIKE);
           }
           LikeDTO newLike = new LikeDTO();
           
           final UUID id = getNewUUID();
           final Date date = DateHelper.getNow();
           final UserDTO user = like.getUser();
           final ArtworkDTO artwork = like.getArtwork();
           
           newLike.setId(id);
           newLike.setRealizationDate(date);
           newLike.setUser(user);
           newLike.setArtwork(artwork);
           
           factory.getLikeDAO().create(newLike);
       
       }catch(UsecaseCustomException exception) {
           throw exception;
       } catch(ArtdlyCustomException exception) {
           throw UsecaseCustomException.wrapException(Messages.CreateLikeUsecaseImpl.TECHNICAL_PROBLEM_CREATE_LIKE, exception);
       } catch(Exception exception) {
           throw UsecaseCustomException.CreateBusinessException(Messages.CreateLikeUsecaseImpl.TECHNICAL_UNEXPECTED_PROBLEM_CREATE_LIKE, exception);
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

       

        
    
       