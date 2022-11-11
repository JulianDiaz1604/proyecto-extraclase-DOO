package edu.uco.artdly.service.usecase.user.implementation;

import java.util.List;
import java.util.UUID;

import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.UserDTO;
import edu.uco.artdly.service.usecase.user.FindUserByIdUsecase;

public class FindUserByIdUsecaseImpl implements FindUserByIdUsecase{

    private final DAOFactory factory;

    public FindUserByIdUsecaseImpl(DAOFactory factory){
        this.factory = factory;
    }

    @Override
    public UserDTO execute(UUID id) {
        
        UserDTO result = new UserDTO();
        final UserDTO user = UserDTO.create(id);
        final List<UserDTO> results = factory.getUserDAO().find(user);

        if(!results.isEmpty()){
            result = results.get(0);
        }

        return result;

    }
    
}
