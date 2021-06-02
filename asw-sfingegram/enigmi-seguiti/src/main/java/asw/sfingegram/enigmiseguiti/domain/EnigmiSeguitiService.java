package asw.sfingegram.enigmiseguiti.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*; 
import java.util.stream.*; 

@Service 
public class EnigmiSeguitiService {

	@Autowired 
	private ConnessioniTipiService connessioniTipiService;

	@Autowired 
	private ConnessioniAutoriService connessioniAutoriService;

	@Autowired 
	private EnigmiService enigmiService;

	@Autowired 
	private EnigmiSeguitiRepository enigmiSeguitiRepository;

	/* Trova gli enigmi (in formato breve) degli utenti seguiti da utente. */ 
	public Collection<EnigmiSeguiti> getEnigmiSeguiti(String utente) {
		return enigmiSeguitiRepository.findByUtente(utente);
	}

	private Collection<Enigma> getEnigmiDiAutoriSeguiti(String utente) {
		Collection<Enigma> enigmi = new TreeSet<>(); 
		Collection<ConnessioneConAutore> connessioniConAutori = connessioniAutoriService.getConnessioniConAutoriByUtente(utente); 
		Collection<String> autoriSeguiti = 
			connessioniConAutori
				.stream()
				.map(c -> c.getAutore())
				.collect(Collectors.toSet()); 
		if (autoriSeguiti.size()>0) {
			Collection<Enigma> enigmiDiAutoriSeguiti = enigmiService.getEnigmiByAutori(autoriSeguiti);
			enigmi.addAll(enigmiDiAutoriSeguiti); 
		}
		return enigmi; 
	}

	private Collection<Enigma> getEnigmiDiTipiSeguiti(String utente) {
		Collection<Enigma> enigmi = new TreeSet<>(); 
		Collection<ConnessioneConTipo> connessioniConTipi = connessioniTipiService.getConnessioniConTipiByUtente(utente); 
		Collection<String> tipiSeguiti = 
			connessioniConTipi
				.stream()
				.map(c -> c.getTipo())
				.collect(Collectors.toSet()); 
		if (tipiSeguiti.size()>0) {
			Collection<Enigma> enigmiDiTipiSeguiti = enigmiService.getEnigmiByTipi(tipiSeguiti);
			enigmi.addAll(enigmiDiTipiSeguiti); 
		}
		return enigmi; 
	}


	public void updateEnigmiSeguiti(Enigma enigma) {
		EnigmiSeguiti es;
		for(ConnessioneConTipo c : connessioniTipiService.getConnessioniConTipiByTipo(enigma.getTipo())) {
			es = new EnigmiSeguiti();
			es.setUtente(c.getUtente());
			es.setIdEnigma(enigma.getId());
			es.setAutoreEnigma(enigma.getAutore());
			es.setTipoEnigma(enigma.getTipo());
			es.setTitoloEnigma(enigma.getTitolo());
			es.setTesto(enigma.getTesto());

			enigmiSeguitiRepository.save(es);
		}

		for(ConnessioneConAutore c : connessioniAutoriService.getConnessioniConAutoreByAutore(enigma.getAutore())) {
			es = new EnigmiSeguiti();
			es.setUtente(c.getUtente());
			es.setIdEnigma(enigma.getId());
			es.setAutoreEnigma(enigma.getAutore());
			es.setTipoEnigma(enigma.getTipo());
			es.setTitoloEnigma(enigma.getTitolo());
			es.setTesto(enigma.getTesto());
			
			enigmiSeguitiRepository.save(es);
		}
	}

	public void updateEnigmiSeguiti(ConnessioneConTipo connessione) {
		EnigmiSeguiti es;
		for(Enigma enigma : enigmiService.getEnigmiByTipo(connessione.getTipo())) {
			es = new EnigmiSeguiti();
			es.setUtente(connessione.getUtente());
			es.setIdEnigma(enigma.getId());
			es.setAutoreEnigma(enigma.getAutore());
			es.setTipoEnigma(enigma.getTipo());
			es.setTitoloEnigma(enigma.getTitolo());
			es.setTesto(enigma.getTesto());

			enigmiSeguitiRepository.save(es);
		}

	}

	public void updateEnigmiSeguiti(ConnessioneConAutore connessione) {
		EnigmiSeguiti es;
		for(Enigma enigma : enigmiService.getEnigmiByAutore(connessione.getAutore())) {
			es = new EnigmiSeguiti();
			es.setUtente(connessione.getUtente());
			es.setIdEnigma(enigma.getId());
			es.setAutoreEnigma(enigma.getAutore());
			es.setTipoEnigma(enigma.getTipo());
			es.setTitoloEnigma(enigma.getTitolo());
			es.setTesto(enigma.getTesto());

			enigmiSeguitiRepository.save(es);
		}
	}

}
