package edu.uco.artdly.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uco.artdly.domain.ArtworkTypeDTO;
import edu.uco.artdly.service.usecase.command.FindAllArtworkTypeCommand;
import edu.uco.artdly.service.usecase.command.implementation.FindAllArtworkTypeCommandImpl;

@RestController
@RequestMapping("/artdly/artworkType")
public class ArtworkTypeController {

	private FindAllArtworkTypeCommand findAllArtworkTypeCommand = new FindAllArtworkTypeCommandImpl();
	
	@GetMapping("/findAll")
	public List<ArtworkTypeDTO> findAllArtworkType(){
		return findAllArtworkTypeCommand.exexute();
	}
	
}
