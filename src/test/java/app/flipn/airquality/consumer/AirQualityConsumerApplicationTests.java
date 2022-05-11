package app.flipn.airquality.consumer;

import app.flipn.airquality.consumer.config.PulsarConfig;
import app.flipn.airquality.consumer.config.PulsarConsumerConfig;
import app.flipn.airquality.consumer.model.Observation;
import app.flipn.airquality.consumer.service.PulsarService;
import org.apache.pulsar.client.api.MessageListener;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.testng.AssertJUnit.assertNotNull;

@SpringBootTest(
		classes = {
				PulsarConfig.class,
				PulsarConsumerConfig.class,
				PulsarService.class,
				MessageListener.class
		}
)
class AirQualityConsumerApplicationTests {

	@Autowired
	private PulsarService pulsarService;

	@Autowired
	MessageListener messageListener;

	@Test
	void contextLoads() {
		System.out.println("test");
	}
}
