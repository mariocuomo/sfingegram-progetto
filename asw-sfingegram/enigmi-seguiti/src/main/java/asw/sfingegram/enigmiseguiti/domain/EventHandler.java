package asw.sfingegram.enigmiseguiti.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import asw.sfingegram.common.api.event.*;

import java.util.logging.*;

@Service
public class EventHandler {

	private final Logger logger = Logger.getLogger(EventHandler.class.toString());

	@Autowired
	private EnigmiService enigmiService;

	@Autowired 
	private ConnessioniTipiService connessioniTipiService;

	@Autowired 
	private ConnessioniAutoriService connessioniAutoriService;

	public void onEvent(DomainEvent event) {
		if(event.getClass().equals(EnigmaCreatedEvent.class)) {
			EnigmaCreatedEvent ece = (EnigmaCreatedEvent) event;
			enigmaCreated(ece);
		} else if(event.getClass().equals(ConnessioneConAutoreCreatedEvent.class)) {
			ConnessioneConAutoreCreatedEvent cceA = (ConnessioneConAutoreCreatedEvent) event;
			connessioneConAutoreCreated(cceA);
		} else if(event.getClass().equals(ConnessioneConTipoCreatedEvent.class)) {
			ConnessioneConTipoCreatedEvent cceT = (ConnessioneConTipoCreatedEvent) event;
			connessioneConTipoCreated(cceT);
		} else {
			logger.warning("UNKNOW EVENT: "+event);
		}
	}

	private void enigmaCreated(EnigmaCreatedEvent event) {
		Enigma enigma = new Enigma();
		enigma.setId(event.getId());
		enigma.setAutore(event.getAutore());
		enigma.setTipo(event.getTipo());
		enigma.setTitolo(event.getTitolo());
		enigma.setTesto(event.getTesto());
		enigma.setSoluzione(event.getSoluzione());

		enigmiService.add(enigma);
	}

	private void connessioneConAutoreCreated(ConnessioneConAutoreCreatedEvent event) {
		ConnessioneConAutore connessione = new ConnessioneConAutore();		connessione.setId(event.getId());
		connessione.setUtente(event.getUtente());
		connessione.setAutore(event.getAutore());

		connessioniAutoriService.add(connessione);
	}

	private void connessioneConTipoCreated(ConnessioneConTipoCreatedEvent event) {
		ConnessioneConTipo connessione = new ConnessioneConTipo();
		connessione.setUtente(event.getUtente());
		connessione.setTipo(event.getTipo());

		connessioniTipiService.add(connessione);
	}

}