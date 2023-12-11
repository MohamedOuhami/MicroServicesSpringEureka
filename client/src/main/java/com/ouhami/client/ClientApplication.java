package com.ouhami.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.ouhami.client.entities.Client;
import com.ouhami.client.repositories.ClientRepository;

@EnableDiscoveryClient
@SpringBootApplication
public class ClientApplication {

    @Autowired
	private ClientRepository clientRepository;

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}

	@Bean
    public CommandLineRunner initializeBaseH2() {
        return args -> {
            clientRepository.save(new Client(1, "Mohamed", "Ouhami", 23.0f));
            clientRepository.save(new Client(2, "Bassma", "Ouhami", 34.0f));
            clientRepository.save(new Client(3, "Noufal", "Zerhouni", 25.0f));
            clientRepository.save(new Client(4, "Anas", "Zniti", 37.0f));

        };
    }

}
