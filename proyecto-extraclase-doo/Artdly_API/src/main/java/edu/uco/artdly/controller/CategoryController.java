package edu.uco.artdly.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uco.artdly.controller.response.Response;
import edu.uco.artdly.crosscutting.messages.Messages;
import edu.uco.artdly.domain.CategoryDTO;
import edu.uco.artdly.service.usecase.command.FindAllCategoryCommand;
import edu.uco.artdly.service.usecase.command.implementation.FindAllCategoryCommandImpl;

@RestController
@RequestMapping("/artdly/category")
public class CategoryController {
	
	private FindAllCategoryCommand findAllCategoryCommand = new FindAllCategoryCommandImpl();
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/findAll")
	public ResponseEntity<Response<CategoryDTO>> findAllCategories(){

		Response<CategoryDTO> response = new Response<>();
		HttpStatus httpStatus = HttpStatus.OK;

		try {
			List<CategoryDTO> categories = findAllCategoryCommand.execute();
			response.setData(categories);
			response.addSuccessMessage(Messages.CategoryController.SUCCESS_FIND_CATEGORY);
		} catch (final Exception exception) {
			httpStatus = HttpStatus.BAD_REQUEST;
			response.addFatalMessage(Messages.CategoryController.TECHNICAL_UNEXPECTED_PROBLEM_FATALERROR);
			exception.printStackTrace();
		}

		return new ResponseEntity<>(response, httpStatus);
		
	}
	
}
