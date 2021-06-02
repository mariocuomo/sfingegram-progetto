package asw.sfingegram.enigmiseguiti.rest;

import asw.sfingegram.enigmiseguiti.domain.*; 

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.server.ResponseStatusException;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant; 
import java.time.Duration; 

import java.util.logging.Logger; 
import java.util.*; 

@RestController
public class EnigmiSeguitiController {

	private final Logger logger = Logger.getLogger(EnigmiSeguitiController.class.toString()); 

	@Autowired 
	private EnigmiSeguitiService enigmiSeguitiService;

	@Autowired 
	private EnigmiService enigmiService;

	@Autowired 
	private ConnessioniAutoriService connessioniAutoriService;

	@Autowired 
	private ConnessioniTipiService connessioniTipiService;

	/* Trova gli enigmi (in formato breve) degli utenti seguiti da utente. */ 
	@GetMapping("/enigmiseguiti/{utente}")
	public Collection<EnigmiSeguiti> getEnigmiSeguiti(@PathVariable String utente) {
		Instant start = Instant.now();
		logger.info("REST CALL: getEnigmiSeguiti " + utente); 
		Collection<EnigmiSeguiti> enigmi = enigmiSeguitiService.getEnigmiSeguiti(utente); 
		Duration duration = Duration.between(start, Instant.now()); 
		logger.info("getEnigmiSeguiti " + utente + " (trovati " + enigmi.size() + " enigmi in " + duration.toMillis() + " ms): " + enigmi);
		return enigmi; 
	}

	@GetMapping("/tuttienigmidiautore/{autore}")
	public Collection<Enigma> getTuttiEnigmiDiAutore(@PathVariable String autore) {
		Collection<Enigma> enigmi = enigmiService.getEnigmiByAutore(autore); 
		return enigmi; 
	}

}
