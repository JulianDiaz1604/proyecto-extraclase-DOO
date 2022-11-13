package edu.uco.artdly.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/artdly/user")
public class UserController {

	
	public String hola() {
		return "hola mundo";
	}
}
