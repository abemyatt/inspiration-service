package myatt.abe.inspirations;

import jakarta.annotation.PostConstruct;
import myatt.abe.inspirations.service.discord.InspirationForTheBoysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class Main {

	@Autowired
	private InspirationForTheBoysService inspirationForTheBoysService;

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}


	@PostConstruct
	public void startBot() {
		inspirationForTheBoysService.startBot();
	}
}
