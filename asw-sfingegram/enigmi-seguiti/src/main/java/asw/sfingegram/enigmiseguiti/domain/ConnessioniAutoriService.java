package asw.sfingegram.enigmiseguiti.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*; 

@Service 
public class ConnessioniAutoriService {

	@Autowired
	private ConnessioniAutoriRepository connessioneAutoriRepository;

	@Autowired
	private EnigmiSeguitiService enigmiSeguitiService;


	public Collection<ConnessioneConAutore> getConnessioniConAutoriByUtente(String utente){
		return connessioneAutoriRepository.findAllByUtente(utente);
	} 

	public void add(ConnessioneConAutore connessione) {
		connessioneAutoriRepository.save(connessione);
		enigmiSeguitiService.updateEnigmiSeguiti(connessione);
	}

	public Collection<ConnessioneConAutore> getConnessioniConAutoreByAutore(String autore) {
		return connessioneAutoriRepository.findAllByAutore(autore);
	}

	
}
