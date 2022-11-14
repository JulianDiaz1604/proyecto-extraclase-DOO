package edu.uco.artdly.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uco.artdly.controller.response.Response;
import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.service.usecase.command.FindAllArtworkTypeCommand;
import edu.uco.artdly.service.usecase.command.implementation.FindAllArtworkTypeCommandImpl;

@RestController
@RequestMapping("/artdly/artworkType")
public class ArtworkTypeController {

	private FindAllArtworkTypeCommand findAllArtworkTypeCommand = new FindAllArtworkTypeCommandImpl();
	
	@GetMapping("/findAll")
	public ResponseEntity<Response<ArtworkTypeDTO>> findAllArtworkType(){
		
		Response<ArtworkTypeDTO> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			List<ArtworkTypeDTO> artworkTypes = findAllArtworkTypeCommand.execute();
			response.setData(artworkTypes);
			response.addSuccessMessage("Se devolvieron los tipos de obras almacenados");
		} catch (final Exception exception) {
			httpStatus = HttpStatus.BAD_REQUEST;
			response.addFatalMessage("Solo Dios sabe que pasó, animo tigre");
			exception.printStackTrace();
		}

		return new ResponseEntity<>(response, httpStatus);
		
	}
	
}
