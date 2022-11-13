package edu.uco.artdly.controller.validator;

import java.util.List;

import edu.uco.artdly.crosscutting.messages.Message;

public interface Validator<T> {
	
	List<Message> validate(T dto);

}
