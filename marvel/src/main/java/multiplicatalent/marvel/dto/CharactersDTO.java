package multiplicatalent.marvel.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharactersDTO {

	private String last_sync;
	
	private List<HeroesInteractedDTO> characters;

}
