package asw.sfingegram.enigmiseguiti.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.*; 

public interface ConnessioniTipiRepository extends CrudRepository<ConnessioneConTipo, Long> {

	public Collection<ConnessioneConTipo> findAllByUtente(String utente);

	public Collection<ConnessioneConTipo> findAllByTipo(String tipo);

	public Collection<ConnessioneConTipo> findByTipoStartingWith(String tipo);

}
