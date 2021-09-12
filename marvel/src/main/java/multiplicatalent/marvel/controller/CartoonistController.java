package multiplicatalent.marvel.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import multiplicatalent.marvel.dto.CartoonistDTO;
import multiplicatalent.marvel.services.CartoonistService;

@RestController
@RequestMapping("/marvel")
public class CartoonistController {
	@Autowired
	CartoonistService cartoonistService;
	
	@GetMapping("/colaborators/{name}")
	public CartoonistDTO getCollaborators(@PathVariable("name") String name) throws ParseException {
		return cartoonistService.getCollaborators(name);
	}

}
