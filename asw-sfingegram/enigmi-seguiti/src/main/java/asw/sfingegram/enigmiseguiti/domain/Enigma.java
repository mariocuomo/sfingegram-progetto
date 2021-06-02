package asw.sfingegram.enigmiseguiti.domain;

import lombok.*; 
import javax.persistence.*; 

/* Enigma (in formato breve, senza soluzione). */ 
@Data @NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Enigma implements Comparable<Enigma> {

	@EqualsAndHashCode.Include
	@Id
	private Long id; 
	private String autore; 
	private String tipo; 
	private String titolo; 
	private String[] testo; 
	private String[] soluzione; 

	@Override
	public int compareTo(Enigma other) {
		return this.id.compareTo(other.id); 
	}
	
}
