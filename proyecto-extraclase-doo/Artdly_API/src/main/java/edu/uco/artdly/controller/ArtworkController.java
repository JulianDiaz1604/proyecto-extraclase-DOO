package edu.uco.artdly.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.service.usecase.command.CreateArtworkCommand;
import edu.uco.artdly.service.usecase.command.FindAllArtworkCommand;
import edu.uco.artdly.service.usecase.command.implementation.CreateArtworkCommandImpl;
import edu.uco.artdly.service.usecase.command.implementation.FindAllArtworkCommandImpl;

@RestController
@RequestMapping("/artdly/artwork")
public class ArtworkController {
	
	private CreateArtworkCommand createArtworkCommand = new CreateArtworkCommandImpl();
	List<String> files = new ArrayList<>();
	private final Path rootLocation = Paths.get("C:/Users/diazj/OneDrive/Escritorio");
	private FindAllArtworkCommand findAllArtwork = new FindAllArtworkCommandImpl();

	@GetMapping
	public ArtworkDTO artwork(){
		return new ArtworkDTO();
	}
	@CrossOrigin(origins = "http://localhost:4200")
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
				response.addSuccessMessage(Messages.ArtworkController.TECHNICAL_PROBLEM_CREATE_ARTWORK);
			} else {
				 httpStatus = HttpStatus.BAD_REQUEST;
				 response.setMessages(messages);
			}
		} catch(final ArtdlyCustomException exception) {
			httpStatus = HttpStatus.BAD_REQUEST;

			if(exception.isTechinalException()) {
				response.addErrorMessage(Messages.ArtworkController.TECHNICAL_PROBLEM_CREATE_ARTWORK);
			}else {
				response.addErrorMessage(exception.getMessage());
			}
			exception.printStackTrace();
		}catch(final Exception exception) {
			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
			response.addFatalMessage(Messages.ArtworkController.TECHNICAL_UNEXPECTED_PROBLEM_FATALERROR);
			exception.printStackTrace();

		}
			
		return new ResponseEntity<>(response,httpStatus);
	}
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/savefile")
    public ResponseEntity<Response<String>> handleFileUpload(@RequestParam("file") MultipartFile file) {
        Response<String> response  = new Response<>();
        HttpStatus httpStatus = HttpStatus.OK;
           try {
              Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
               files.add(file.getOriginalFilename());
               response.addInfoMessage(Messages.ArtworkController.SUCCESS_SAVE_FILE);
           }catch (final ArtdlyCustomException exception) {
				httpStatus = HttpStatus.BAD_REQUEST;
                if(exception.isTechinalException()) {
                    response.addErrorMessage(Messages.ArtworkController.THECNICAL_PROBLEM_CREATE_FILE);
                }else {
                    response.addErrorMessage(exception.getMessage());
                }
           }catch (Exception exception) {
				httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
               response.addFatalMessage(Messages.ArtworkController.FATAL_PROBLEM_CREATE_FILE);
           }
           return new ResponseEntity<>(response,httpStatus);
		}
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/findall")
	public ResponseEntity<Response<ArtworkDTO>>	findAllArtworks(){
		Response<ArtworkDTO> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;
		try {
			List<ArtworkDTO> artworks = findAllArtwork.execute();
			response.setData(artworks);
			response.addSuccessMessage(Messages.ArtworkController.SUCCESS_FIND_ARTWORK);
		} catch (final Exception exception) {
			httpStatus = HttpStatus.BAD_REQUEST;
			response.addFatalMessage(Messages.ArtworkController.TECHNICAL_UNEXPECTED_PROBLEM_FATALERROR);
			exception.printStackTrace();
		}

		return new ResponseEntity<>(response, httpStatus);
		
		
	}
	
	
}
