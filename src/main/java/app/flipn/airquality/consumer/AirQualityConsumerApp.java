package app.flipn.airquality.consumer;

import app.flipn.airquality.consumer.model.Observation;
import app.flipn.airquality.consumer.service.PulsarService;
import org.apache.pulsar.client.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;

//import java.util.Calendar;
import java.util.List;

//@EnableScheduling
@SpringBootApplication
public class AirQualityConsumerApp implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(AirQualityConsumerApp.class);

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(AirQualityConsumerApp.class, args);
	}

	@Autowired
	private PulsarService pulsarService;

	/**
	 * For a more manual way to consume with a scheduler, call this
	 */
	private void display() {
		// Pulsar
		List<Observation> observationList = pulsarService.consume();

		if ( observationList != null && observationList.size() > 0) {
			for (Observation observation : observationList) {
				if ( observation != null) {
					log.info(observation.toString());
				}
			}
		}
	}

//	@Scheduled(initialDelay = 0, fixedRate = 30000)
//	public void repeatRun()
//	{
//		log.debug("Repeat Run. Current time is :: " + Calendar.getInstance().getTime());
//		//display();
//	}

	@Override
	public void run(String... args) {
		//display();
	}
}
