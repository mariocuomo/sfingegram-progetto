package asw.sfingegram.enigmiseguiti.domain;

import lombok.*; 
import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode
@NoArgsConstructor
public class EnigmiSeguitiId implements Serializable {
	private String utente; 
	private Long idEnigma; 

    public EnigmiSeguitiId(String utente, Long idEnigma) {
        this.utente = utente;
        this.idEnigma = idEnigma;
    }
}
