package edu.uco.artdly.controller.validator.user;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uco.artdly.controller.validator.Validator;
import edu.uco.artdly.crosscutting.helper.MailHelper;
import edu.uco.artdly.crosscutting.helper.UUIDHelper;
import edu.uco.artdly.crosscutting.messages.Message;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.domain.UserDTO;

public class CreateUserValidator implements Validator<UserDTO>{

	@Override
	public List<Message> validate(UserDTO dto) {
		List<Message> messages = new ArrayList<>();
		validateUserId(dto.getId(), messages);
		validateMail(dto.getMail(), messages);
		return messages;
	}

	public void validateUserId(UUID userId, List<Message> messages) {
		if(UUIDHelper.isDefaultUUID(userId)) {
			userId = UUIDHelper.getNewUUID();
		}
	}

	public void validateUserBirthdate(Date userDate, List<Message> messages) {
		if(isMinor(userDate)) {
			messages.add(Message.createInfoMessage(Messages.CreateUserValidator.TECHNICAL_PROBLEM_VALIDATION_BIRTHDAY));
		}
		
	}

	public void validateMail(String mail, List<Message> messages) {
		if(MailHelper.isDefaultMail(mail)) {
			messages.add(Message.createErrorMessage(Messages.CreateUserValidator.TECHNICAL_PROBLEM_VALIDATION_MAIL));
		}
	}

	public static final int yearsOld(Date birthdate) {
        Date now = Date.valueOf(LocalDate.now());
        Period year = Period.between(birthdate.toLocalDate(), now.toLocalDate());
        return year.getYears();
    }

    public static final boolean isMinor(Date birthdate){
        return yearsOld(birthdate) < 18;
    }
}
