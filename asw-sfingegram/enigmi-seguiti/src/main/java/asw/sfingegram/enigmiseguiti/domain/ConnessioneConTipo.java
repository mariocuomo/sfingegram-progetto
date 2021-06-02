package asw.sfingegram.enigmiseguiti.domain;

import lombok.*; 
import javax.persistence.*; 


@Data @NoArgsConstructor
@Entity
public class ConnessioneConTipo {
	@Id
	@GeneratedValue
	private Long id; 
	private String utente; 
	private String tipo; 
	
}
