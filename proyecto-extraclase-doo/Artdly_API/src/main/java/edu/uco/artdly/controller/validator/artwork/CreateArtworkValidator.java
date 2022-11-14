package edu.uco.artdly.controller.validator.artwork;

import java.util.ArrayList;
import java.util.List;

import edu.uco.artdly.controller.validator.Validator;
import edu.uco.artdly.crosscutting.helper.ObjectHelper;
import edu.uco.artdly.crosscutting.helper.StringHelper;
import edu.uco.artdly.crosscutting.messages.Message;
import edu.uco.artdly.domain.ArtworkDTO;

public class CreateArtworkValidator implements Validator<ArtworkDTO> {

    @Override
	public List<Message> validate(ArtworkDTO dto) {
		List<Message> messages = new ArrayList<>();
		validateArtworkTittle(dto.getTittle(), messages);
		return messages;
	}

    private void validateArtworkTittle(String artworkTittle, List<Message> messages){
        if (ObjectHelper.isNull(artworkTittle)) {
            messages.add(Message.createInfoMessage("Debe asignarle un titulo a la obra"));
        }
        if (StringHelper.isDefaultString(artworkTittle.trim())) {
            messages.add(Message.createInfoMessage("Debe asignarle un titulo a la obra"));
        }
    }

}
