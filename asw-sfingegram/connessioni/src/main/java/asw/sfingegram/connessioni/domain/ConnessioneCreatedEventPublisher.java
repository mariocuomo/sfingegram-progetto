package asw.sfingegram.connessioni.domain;

import asw.sfingegram.common.api.event.DomainEvent;

public interface ConnessioneCreatedEventPublisher {

	public void publish(DomainEvent event);

}