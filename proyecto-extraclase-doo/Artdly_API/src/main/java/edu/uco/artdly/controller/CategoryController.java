package edu.uco.artdly.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.uco.artdly.domain.CategoryDTO;
import edu.uco.artdly.service.usecase.command.FindAllCategoryCommand;
import edu.uco.artdly.service.usecase.command.implementation.FindAllCategoryCommandImpl;

@RestController
@RequestMapping("/artdly/category")
public class CategoryController {
	
	private FindAllCategoryCommand findAllCategoryCommand = new FindAllCategoryCommandImpl();

	@GetMapping("/findAll")
	public List<CategoryDTO> findAllCategories(){
		return findAllCategoryCommand.execute();
	}
	
	@GetMapping()
	public String hla() {
		return "hola";
	}
	
}
