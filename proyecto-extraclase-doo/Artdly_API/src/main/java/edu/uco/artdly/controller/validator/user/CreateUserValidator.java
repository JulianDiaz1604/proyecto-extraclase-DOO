package edu.uco.artdly.controller.validator.user;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.artdly.controller.validator.Validator;
import edu.uco.artdly.crosscutting.helper.MailHelper;
import edu.uco.artdly.crosscutting.helper.UUIDHelper;
import edu.uco.artdly.crosscutting.messages.Message;
import edu.uco.artdly.domain.UserDTO;
import static edu.uco.artdly.crosscutting.helper.DateHelper.isMinor;

public class CreateUserValidator implements Validator<UserDTO>{

	@Override
	public List<Message> validate(UserDTO dto) {
		List<Message> messages = new ArrayList<>();
		validateUserId(dto.getId(), messages);
		validateMail(dto.getMail(), messages);
		validateUserBirthdate(dto.getBirthDate(), messages);
		return messages;
	}
	public void validateUserId(UUID userId, List<Message> messages) {
		if(UUIDHelper.isDefaultUUID(userId)) {
			messages.add(Message.createErrorMessage("el id es el mismo que el default"));
		}
	}
	public void validateUserBirthdate(Date userDate, List<Message> messages) {
		if(isMinor(userDate)) {
			messages.add(Message.createInfoMessage("usted es menor de edad"));
		}
		
		
	}
	public void validateMail(String mail, List<Message> messages) {
		if(MailHelper.isDefaultMail(mail)) {
			messages.add(Message.createErrorMessage("el mail es igual que el Default mail"));
		}

}
}
