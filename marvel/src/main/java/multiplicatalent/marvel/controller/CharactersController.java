package multiplicatalent.marvel.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import multiplicatalent.marvel.dto.CharactersDTO;
import multiplicatalent.marvel.services.CharactersServices;

@RestController
@RequestMapping("/marvel")
public class CharactersController {
	
	@Autowired
	CharactersServices characterServices;
	
	@GetMapping("/characters/{name}")
	public CharactersDTO getHeroesInteracted(@PathVariable("name") String name) throws ParseException {
		return characterServices.getHeroeInteracted(name);
	}

}
