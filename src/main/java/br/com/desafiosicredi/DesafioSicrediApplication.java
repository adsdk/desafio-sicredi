package br.com.desafiosicredi;

import br.com.desafiosicredi.config.QueueConfig;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableRabbit
@EnableScheduling
@SpringBootApplication
@EnableConfigurationProperties(QueueConfig.class)
public class DesafioSicrediApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioSicrediApplication.class, args);
	}

}
