package asw.sfingegram.connessioni.eventpublisher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import asw.sfingegram.connessioni.domain.ConnessioneCreatedEventPublisher;
import asw.sfingegram.common.api.event.DomainEvent;

@Component
public class ConnessioneCreatedEventImpl implements ConnessioneCreatedEventPublisher {

	@Autowired
	private KafkaTemplate<String, DomainEvent> template;

	@Value("${asw.kafka.channel.connessioni.out}")
	private String channel;

	public void publish(DomainEvent event) {
		template.send(channel, event);
	}
}