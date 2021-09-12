package multiplicatalent.marvel.dto;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HeroesInteractedDTO {
	
	private String character;
	
	@JsonProperty("Comics")
	private ArrayList<String> comics;

}
