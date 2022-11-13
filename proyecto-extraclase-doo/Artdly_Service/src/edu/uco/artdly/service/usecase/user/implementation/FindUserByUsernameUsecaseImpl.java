package edu.uco.artdly.service.usecase.user.implementation;

import java.util.List;

import edu.uco.artdly.data.daofactory.DAOFactory;
import edu.uco.artdly.domain.UserDTO;
import edu.uco.artdly.service.usecase.user.FindUserByUsernameUsecase;

public class FindUserByUsernameUsecaseImpl implements FindUserByUsernameUsecase{

    private final DAOFactory factory;

    public  FindUserByUsernameUsecaseImpl(DAOFactory factory) {
        this.factory = factory;

    }

    @Override
    public UserDTO execute(String username) {
        
        UserDTO result = new UserDTO();
        final UserDTO user = UserDTO.create(username);
        final List<UserDTO> results = factory.getUserDAO().find(user);

        if(!results.isEmpty()){
            result = results.get(0);
        }

        return result;

    }
    

}
