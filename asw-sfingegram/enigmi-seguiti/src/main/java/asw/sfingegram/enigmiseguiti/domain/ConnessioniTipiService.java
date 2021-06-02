package asw.sfingegram.enigmiseguiti.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*; 

@Service 
public class ConnessioniTipiService {

	@Autowired
	private ConnessioniTipiRepository connessioneTipiRepository;

	@Autowired
	private EnigmiSeguitiService enigmiSeguitiService;
	
	public Collection<ConnessioneConTipo> getConnessioniConTipiByUtente(String utente){
		return connessioneTipiRepository.findAllByUtente(utente);
	}

	public void add(ConnessioneConTipo connessione) {
		connessioneTipiRepository.save(connessione);
		enigmiSeguitiService.updateEnigmiSeguiti(connessione);
	}


	public Collection<ConnessioneConTipo> getConnessioniConTipiByTipo(String tipo) {
		return connessioneTipiRepository.findByTipoStartingWith(tipo);
	}

	
}
