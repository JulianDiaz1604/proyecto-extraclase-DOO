package edu.uco.artdly.controller;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.domain.LikeDTO;

import edu.uco.artdly.service.usecase.command.CreateLikeCommand;
import edu.uco.artdly.service.usecase.command.DeleteLikeCommand;
import edu.uco.artdly.service.usecase.command.implementation.CreateLikeCommandImpl;
import edu.uco.artdly.service.usecase.command.implementation.DeleteLikeCommandImpl;


@RestController
@RequestMapping("/artdly/like")
public class LikeController {
	private CreateLikeCommand createLikeCommand = new CreateLikeCommandImpl ();
	private DeleteLikeCommand deleteLikeCommand = new DeleteLikeCommandImpl ();
	
	
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

}
