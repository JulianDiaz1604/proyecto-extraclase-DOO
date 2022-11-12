package edu.uco.artdly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uco.artdly.domain.ArtworkDTO;
import edu.uco.artdly.service.usecase.command.PostArtworkCommand;

@RestController
@RequestMapping("/artdly/artwork")
public class ArtworkController {
	
	private PostArtworkCommand postArtworkCommand;

	@PostMapping("/postArtwork")
	public void postArtwork(@RequestBody ArtworkDTO artwork, @RequestBody String pathName) {
		postArtworkCommand.execute(artwork, pathName);
	}
	
}
