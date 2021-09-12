package multiplicatalent.marvel.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import multiplicatalent.marvel.dto.CartoonistDTO;

@Service
public class CartoonistService {

	public CartoonistDTO getCollaborators(String hero) throws ParseException {
		CartoonistDTO collaborators = new CartoonistDTO();
		String realName = hero.contentEquals("capamerica") ? "Captain America" : "Iron Man";
		final String url = "http://gateway.marvel.com/v1/public/characters?apikey=5e1039049018dfa5fb8a67d2217ba5bd&ts=1&hash=f60d4354857fedb5941e7c7ae2c47ebf&name=" + realName;
		RestTemplate restTemplate = new RestTemplate();
		String resultAPI = restTemplate.getForObject(url, String.class);
		
		JSONObject jsonObject = new JSONObject(resultAPI);
		JSONObject data = jsonObject.getJSONObject("data").getJSONArray("results").getJSONObject(0);
		JSONArray comics = data.getJSONObject("comics").getJSONArray("items");
		ArrayList<String> writers = new ArrayList<String>();
		ArrayList<String> editors = new ArrayList<String>();
		ArrayList<String> colorists = new ArrayList<String>();
		for(int i=0; i<comics.length(); i++) {
			String getComicKey = comics.getJSONObject(i).getString("resourceURI");
			final String comicURL = getComicKey + "?apikey=5e1039049018dfa5fb8a67d2217ba5bd&ts=1&hash=f60d4354857fedb5941e7c7ae2c47ebf&comics=43495";
			RestTemplate restTemplateComic = new RestTemplate();
			String resultAPIComic = restTemplateComic.getForObject(comicURL, String.class);
			JSONObject jsonObjectComic = new JSONObject(resultAPIComic);
			JSONArray creators = jsonObjectComic.getJSONObject("data").getJSONArray("results").getJSONObject(0).getJSONObject("creators").getJSONArray("items");
			if(creators.length()!=0) {
				for(int j=0; j<creators.length(); j++) {
					if(creators.getJSONObject(j).getString("role").contentEquals("editor")) {
						editors.add(creators.getJSONObject(j).getString("name"));
					}
					
					if(creators.getJSONObject(j).getString("role").contentEquals("writer")) {
						writers.add(creators.getJSONObject(j).getString("name"));
					}
					
					if(creators.getJSONObject(j).getString("role").contentEquals("colorist")) {
						colorists.add(creators.getJSONObject(j).getString("name"));
					}
				}
			}
		}
		String lastModified = data.getString("modified").toString();
		collaborators.setLast_sync(modifyDateLayout(lastModified));
		collaborators.setWriters(writers);
		collaborators.setEditors(editors);
		collaborators.setColorists(colorists);
		return collaborators;
	}
	
	private String modifyDateLayout(String inputDate) throws ParseException{
	    Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").parse(inputDate);
	    return new SimpleDateFormat("dd/mm/yyyy hh:mm:ss").format(date);
	}
}
