package asw.sfingegram.connessioni.domain;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger; 
import java.util.*; 


import asw.sfingegram.common.api.event.*; 

@Service
public class ConnessioniService {

	@Autowired
	private ConnessioniConAutoriRepository connessioniConAutoriRepository;

	@Autowired
	private ConnessioniConTipiRepository connessioniConTipiRepository;

	@Autowired
	private ConnessioneCreatedEventPublisher eventPublisher;

 	public ConnessioneConAutore createConnessioneConAutore(String utente, String autore) {
		ConnessioneConAutore connessione = new ConnessioneConAutore(utente, autore); 
		connessione = connessioniConAutoriRepository.save(connessione);


		DomainEvent eventPublisherAutore = new ConnessioneConAutoreCreatedEvent(connessione.getId(),connessione.getUtente(), connessione.getAutore());
		eventPublisher.publish(eventPublisherAutore);

		return connessione;
	}

 	public ConnessioneConTipo createConnessioneConTipo(String utente, String tipo) {
		ConnessioneConTipo connessione = new ConnessioneConTipo(utente, tipo); 
		connessione = connessioniConTipiRepository.save(connessione);

		DomainEvent eventPublisherType = new ConnessioneConTipoCreatedEvent(connessione.getId(),connessione.getUtente(), connessione.getTipo());
		eventPublisher.publish(eventPublisherType);

		return connessione;
	}

 	public ConnessioneConAutore getConnessioneConAutore(Long id) {
		ConnessioneConAutore connessione = connessioniConAutoriRepository.findById(id).orElse(null);
		return connessione;
	}

 	public ConnessioneConTipo getConnessioneConTipo(Long id) {
		ConnessioneConTipo connessione = connessioniConTipiRepository.findById(id).orElse(null);
		return connessione;
	}

 	public Collection<ConnessioneConAutore> getConnessioniConAutori() {
		Collection<ConnessioneConAutore> connessioni = connessioniConAutoriRepository.findAll();
		return connessioni;
	}

 	public Collection<ConnessioneConTipo> getConnessioniConTipi() {
		Collection<ConnessioneConTipo> connessioni = connessioniConTipiRepository.findAll();
		return connessioni;
	}

	public Collection<ConnessioneConAutore> getConnessioniConAutoriByUtente(String utente) {
		Collection<ConnessioneConAutore> connessioni = connessioniConAutoriRepository.findByUtente(utente);
		return connessioni;
	}

	public Collection<ConnessioneConTipo> getConnessioniConTipiByUtente(String utente) {
		Collection<ConnessioneConTipo> connessioni = connessioniConTipiRepository.findByUtente(utente);
		return connessioni;
	}

}
