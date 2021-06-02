package asw.sfingegram.enigmiseguiti.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

public interface ConnessioniAutoriRepository extends CrudRepository<ConnessioneConAutore, Long> {

	public Collection<ConnessioneConAutore> findAllByUtente(String utente);

	public Collection<ConnessioneConAutore> findAllByAutore(String autore);

}
