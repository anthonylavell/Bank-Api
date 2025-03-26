package com.hubspot.assessment;
import com.hubspot.assessment.service.CallService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ConcurrentCallsApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConcurrentCallsApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(CallService callService) {
		return args -> {
			callService.processCallData();
		};
	}
}

