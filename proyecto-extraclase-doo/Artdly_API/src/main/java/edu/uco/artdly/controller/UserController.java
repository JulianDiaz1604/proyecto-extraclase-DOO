package edu.uco.artdly.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uco.artdly.controller.response.Response;
import edu.uco.artdly.controller.validator.Validator;
import edu.uco.artdly.controller.validator.user.CreateUserValidator;
import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.messages.Message;
import edu.uco.artdly.domain.UserDTO;
import edu.uco.artdly.service.usecase.command.CreateUserCommand;
import edu.uco.artdly.service.usecase.command.implementation.CreateUserCommandImpl;

@RestController
@RequestMapping("/artdly/user")
public class UserController {
	private CreateUserCommand createUserCommand = new CreateUserCommandImpl();

	@GetMapping
	public UserDTO mostrarUSer() {
		return new UserDTO();
	}
	@PostMapping
	public ResponseEntity<Response<UserDTO>> create(@RequestBody UserDTO user) {
		Response<UserDTO> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			Validator<UserDTO> validator = new CreateUserValidator();
			List<Message> messages = validator.validate(user);
			if(messages.isEmpty()) {
			createUserCommand.execute(user);
			final List<UserDTO> data = new ArrayList<>();
			data.add(user);
			response.setData(data);
			response.addSuccessMessage("la persona se creó chimbisima");
			}
			else {
				 httpStatus = HttpStatus.BAD_REQUEST;
				 response.setMessages(messages);
			}
			}catch(final ArtdlyCustomException exception) {
				 httpStatus = HttpStatus.BAD_REQUEST;

				if(exception.isTechinalException()) {
				response.addErrorMessage("es un error tratando de crear el budget, please try again.......");
				}else {
				response.addErrorMessage(exception.getMessage());}
				exception.printStackTrace();
			}catch(final Exception exception) {
				 httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
				response.addFatalMessage("se nos fue a la mierda esto es un error inesperado!!!!!!!");
				exception.printStackTrace();

			}
			
		return new ResponseEntity<>(response,httpStatus);
	}
}
