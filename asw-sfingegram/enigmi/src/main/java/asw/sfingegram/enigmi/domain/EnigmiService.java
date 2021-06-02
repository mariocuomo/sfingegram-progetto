package asw.sfingegram.enigmi.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger; 
import java.util.*; 
import java.util.stream.*; 

import asw.sfingegram.common.api.event.DomainEvent;
import asw.sfingegram.common.api.event.EnigmaCreatedEvent;

@Service
public class EnigmiService {

	@Autowired
	private EnigmiRepository enigmiRepository;

	@Autowired
	private EnigmaCreatedEventPublisher eventPublisher;

 	public Enigma createEnigma(String autore, String tipo, String titolo, String[] testo, String[] soluzione) {
		Enigma enigma = new Enigma(autore, tipo, titolo, testo, soluzione); 
		enigma = enigmiRepository.save(enigma);

		DomainEvent enigmaCreatedEvent = new EnigmaCreatedEvent(enigma.getId(), enigma.getAutore(), enigma.getTipo(), enigma.getTitolo(), enigma.getTesto(), enigma.getSoluzione());
		eventPublisher.publish(enigmaCreatedEvent);

		return enigma;
	}

 	public Enigma getEnigma(Long id) {
		Enigma enigma = enigmiRepository.findById(id).orElse(null);
		return enigma;
	}

	public Collection<Enigma> getEnigmi() {
		Collection<Enigma> enigmi = enigmiRepository.findAll();
		return enigmi;
	}

	public Collection<Enigma> getEnigmiByAutore(String autore) {
		Collection<Enigma> enigmi = enigmiRepository.findByAutore(autore);
		return enigmi;
	}

	public Collection<Enigma> getEnigmiByAutori(Collection<String> autori) {
		Collection<Enigma> enigmi = enigmiRepository.findByAutoreIn(autori);
		return enigmi;
	}

	public Collection<Enigma> getEnigmiByTipo(String tipo) {
		Collection<Enigma> enigmi = enigmiRepository.findByTipoStartingWith(tipo);
		return enigmi;
	}

	public Collection<Enigma> getEnigmiByTipi(Collection<String> tipi) {
		Collection<Enigma> enigmi = 
			tipi
				.stream()
				.flatMap(t -> enigmiRepository.findByTipoStartingWith(t).stream())
				.collect(Collectors.toSet()); 
		return enigmi;
	}

}
