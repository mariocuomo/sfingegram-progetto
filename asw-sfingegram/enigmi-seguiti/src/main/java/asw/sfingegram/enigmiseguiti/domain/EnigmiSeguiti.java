package asw.sfingegram.enigmiseguiti.domain;

import lombok.*; 
import javax.persistence.*; 

/* Enigma (in formato breve, senza soluzione). */ 
@Data @NoArgsConstructor
@Entity
@IdClass(EnigmiSeguitiId.class)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class EnigmiSeguiti{
	@Id
	private String utente; 
	@Id
	private Long idEnigma; 
	private String autoreEnigma; 
	private String tipoEnigma; 
	private String titoloEnigma;
	private String[] testo; 
}
