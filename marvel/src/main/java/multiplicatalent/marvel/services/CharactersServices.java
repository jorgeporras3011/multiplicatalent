package multiplicatalent.marvel.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import multiplicatalent.marvel.dto.CharactersDTO;
import multiplicatalent.marvel.dto.HeroesInteractedDTO;

@Service
public class CharactersServices {
	
	public CharactersDTO getHeroeInteracted(String hero) throws ParseException {
		
		CharactersDTO character = new CharactersDTO();
		String realName = hero.contentEquals("capamerica") ? "Captain America" : "Iron Man";
		
		final String url = "http://gateway.marvel.com/v1/public/characters?apikey=5e1039049018dfa5fb8a67d2217ba5bd&ts=1&hash=f60d4354857fedb5941e7c7ae2c47ebf&name=" + realName;
		RestTemplate restTemplate = new RestTemplate();
		String resultAPI = restTemplate.getForObject(url, String.class);
		
		JSONObject jsonObject = new JSONObject(resultAPI);
		JSONObject data = jsonObject.getJSONObject("data").getJSONArray("results").getJSONObject(0);
		JSONArray comics = data.getJSONObject("comics").getJSONArray("items");
		List<HeroesInteractedDTO> heroesInteracted = new ArrayList<HeroesInteractedDTO>();
		
		for(int i=0; i<comics.length(); i++) {
			HeroesInteractedDTO heroes = new HeroesInteractedDTO();
			heroes.setCharacter(realName);
			ArrayList<String> comicsArray = new ArrayList<String>();
			String nameComic = comics.getJSONObject(i).getString("name");
			comicsArray.add(nameComic);
			heroes.setComics(comicsArray);
			heroesInteracted.add(heroes);
		}  
		String lastModified = data.getString("modified").toString();
		character.setLast_sync(modifyDateLayout(lastModified));
		character.setCharacters(heroesInteracted);
		return character;
	}
	
	private String modifyDateLayout(String inputDate) throws ParseException{
	    Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").parse(inputDate);
	    return new SimpleDateFormat("dd/mm/yyyy hh:mm:ss").format(date);
	}
}
