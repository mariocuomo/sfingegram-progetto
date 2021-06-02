package asw.sfingegram.enigmi.domain;

import asw.sfingegram.common.api.event.DomainEvent;

public interface EnigmaCreatedEventPublisher {

	public void publish(DomainEvent event);

}