package edu.uco.artdly.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import edu.uco.artdly.crosscutting.messages.Messages;
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
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/register")
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
				response.addSuccessMessage(Messages.UserController.SUCCESS_CREATE_USER);
			}else {
				 httpStatus = HttpStatus.BAD_REQUEST;
				 response.setMessages(messages);
			}
		}catch(final ArtdlyCustomException exception) {
			httpStatus = HttpStatus.BAD_REQUEST;

			if(exception.isTechinalException()) {
				response.addErrorMessage(Messages.UserController.TECHNICAL_PROBLEM_CREATE_USER);
			}else {
				response.addErrorMessage(exception.getMessage());
			}	
			exception.printStackTrace();
		}catch(final Exception exception) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.addFatalMessage(Messages.UserController.TECHNICAL_UNEXPECTED_PROBLEM_FATALERROR);
			exception.printStackTrace();

		}
			
		return new ResponseEntity<>(response,httpStatus);
	}
}
