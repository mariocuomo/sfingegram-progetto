package asw.sfingegram.enigmi.eventpublisher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import asw.sfingegram.enigmi.domain.EnigmaCreatedEventPublisher;
import asw.sfingegram.common.api.event.DomainEvent;

@Component
public class EnigmaCreatedEvent implements EnigmaCreatedEventPublisher {

	@Autowired
	private KafkaTemplate<String, DomainEvent> template;

	@Value("${asw.kafka.channel.enigmi.out}")
	private String channel;

	public void publish(DomainEvent event) {
		template.send(channel, event);
	}
}