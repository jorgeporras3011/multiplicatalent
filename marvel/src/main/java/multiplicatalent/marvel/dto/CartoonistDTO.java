package multiplicatalent.marvel.dto;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartoonistDTO {

	private String last_sync;
	
	private ArrayList<String> writers;
	
	private ArrayList<String> editors;
	
	private ArrayList<String> colorists;
	
}
