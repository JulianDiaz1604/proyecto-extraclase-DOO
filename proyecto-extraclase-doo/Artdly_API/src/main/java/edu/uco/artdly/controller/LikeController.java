package edu.uco.artdly.controller;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uco.artdly.controller.response.Response;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.domain.LikeDTO;
import edu.uco.artdly.service.usecase.command.CreateLikeCommand;
import edu.uco.artdly.service.usecase.command.DeleteLikeCommand;
import edu.uco.artdly.service.usecase.command.FindLikeByUserAndArtworkCommand;
import edu.uco.artdly.service.usecase.command.implementation.CreateLikeCommandImpl;
import edu.uco.artdly.service.usecase.command.implementation.DeleteLikeCommandImpl;
import edu.uco.artdly.service.usecase.command.implementation.FindLikeByUserAndArtworkCommandImpl;

import static edu.uco.artdly.crosscutting.helper.UUIDHelper.getUUIDFromString;;


@RestController
@RequestMapping("/artdly/like")
public class LikeController {
	private CreateLikeCommand createLikeCommand = new CreateLikeCommandImpl ();
	private DeleteLikeCommand deleteLikeCommand = new DeleteLikeCommandImpl ();
	private FindLikeByUserAndArtworkCommand findLikeByUserAndArtworkCommand = new FindLikeByUserAndArtworkCommandImpl();
	
	
	@GetMapping
	public LikeDTO mostrarLike() {
		return new LikeDTO();
	}

	@PostMapping("/register")
	public String  create(@RequestBody LikeDTO like) {
		createLikeCommand.execute(like);
		return Messages.LikeController.TECHNICAL_PROBLEM_REGISTRE_LIKE;
	}

	@DeleteMapping("/delete")
	public void delete(@RequestBody LikeDTO like){
		deleteLikeCommand.execute(like.getId());
	}
	@GetMapping("/{userId}/{artworkId}")
	public ResponseEntity<Boolean> existLike(@PathVariable String userId, @PathVariable String artworkId){
		
		Response<Boolean> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			 boolean exist = findLikeByUserAndArtworkCommand.execute(getUUIDFromString(userId),getUUIDFromString(artworkId));
				if(exist) {
					return new ResponseEntity<>(true, httpStatus);
				}
				else {
					return new ResponseEntity<>(false, httpStatus);
				}
		} catch (final Exception exception) {
			httpStatus = HttpStatus.BAD_REQUEST;
			response.addFatalMessage(Messages.ArtworkTypeController.TECHNICAL_UNEXPECTED_PROBLEM_FATALERROR);
			exception.printStackTrace();
		}
			return new ResponseEntity<>(false , httpStatus);


	
		
		
		
	}
	

}
