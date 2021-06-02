package asw.sfingegram.enigmiseguiti.domain;

import java.util.*; 
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service 
public class EnigmiService {

	@Autowired
	private EnigmaRepository enigmaRepository;

	@Autowired
	private EnigmiSeguitiService enigmiSeguitiService;

	public Collection<Enigma> getEnigmiByAutori(Collection<String> autori){
		return enigmaRepository.findByAutoreIn(autori);
	}

	public Collection<Enigma> getEnigmiByTipi(Collection<String> tipi){
		return enigmaRepository.findByTipoIn(tipi);
	}

	public Collection<Enigma> getEnigmiByAutore(String autore){
		return enigmaRepository.findAllByAutore(autore);
	}

	public Collection<Enigma> getEnigmiByTipo(String tipo){
		return enigmaRepository.findByTipoStartingWith(tipo);
	}


	public void add(Enigma enigma) {
		enigmaRepository.save(enigma);
		enigmiSeguitiService.updateEnigmiSeguiti(enigma);
	}

}
