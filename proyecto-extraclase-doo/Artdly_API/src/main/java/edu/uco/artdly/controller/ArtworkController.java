package edu.uco.artdly.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.uco.artdly.controller.response.Response;
import edu.uco.artdly.controller.validator.Validator;
import edu.uco.artdly.controller.validator.artwork.CreateArtworkValidator;
import edu.uco.artdly.crosscutting.exception.ArtdlyCustomException;
import edu.uco.artdly.crosscutting.messages.Message;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.service.usecase.command.CreateArtworkCommand;
import edu.uco.artdly.service.usecase.command.implementation.CreateArtworkCommandImpl;

@RestController
@RequestMapping("/artdly/artwork")
public class ArtworkController {
	
	private CreateArtworkCommand createArtworkCommand = new CreateArtworkCommandImpl();
	List<String> files = new ArrayList<String>();
	private final Path rootLocation = Paths.get("C:/Users/diazj/OneDrive/Escritorio");

	@PostMapping("/postArtwork")
	public ResponseEntity<Response<ArtworkDTO>> postArtwork(@RequestBody ArtworkDTO artwork) {

		Response<ArtworkDTO> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			Validator<ArtworkDTO> validator = new CreateArtworkValidator();
			List<Message> messages = validator.validate(artwork);
			if(messages.isEmpty()) {
				createArtworkCommand.execute(artwork);
				final List<ArtworkDTO> data = new ArrayList<>();
				data.add(artwork);
				response.setData(data);
				response.addSuccessMessage("la obra se creó chimbisima");
			} else {
				 httpStatus = HttpStatus.BAD_REQUEST;
				 response.setMessages(messages);
			}
		} catch(final ArtdlyCustomException exception) {
			httpStatus = HttpStatus.BAD_REQUEST;

			if(exception.isTechinalException()) {
				response.addErrorMessage("es un error tratando de crear el budget, please try again.......");
			}else {
				response.addErrorMessage(exception.getMessage());
			}
			exception.printStackTrace();
		}catch(final Exception exception) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.addFatalMessage("mka mejor deje la carrera");
			exception.printStackTrace();

		}
			
		return new ResponseEntity<>(response,httpStatus);
	}

	@PostMapping("/savefile")
	public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String message;
		try {
		   try {
			  Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		   } catch (Exception e) {
			  throw new RuntimeException("FAIL!");
		   }
		   files.add(file.getOriginalFilename());
  
		   message = "Successfully uploaded!";
		   return ResponseEntity.status(HttpStatus.OK).body(message);
		} catch (Exception e) {
		   message = "Failed to upload!";
		   return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	 }
	
}
