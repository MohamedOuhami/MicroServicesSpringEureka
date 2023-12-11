package com.ouhami.voiture;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.ouhami.voiture.entities.Client;
import com.ouhami.voiture.entities.Voiture;
import com.ouhami.voiture.repositories.VoitureRepository;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = {"com.ouhami.voiture"})
public class VoitureApplication {

    @Configuration
    public class FeignClientConfig {
        @Bean
        public ClientService clientService(RestTemplate restTemplate) {
            return new ClientService() {
                @Override
                public Client clientById(Integer id) {
                    // Simulated Feign client logic using RestTemplate (replace with actual Feign logic)
                    String clientServiceUrl = "http://localhost:8088"; // Replace with the actual URL of the client service
                    return restTemplate.getForObject(clientServiceUrl + "/client/{id}", Client.class, id);
                }
            };
        }
    }

    @FeignClient
    public interface ClientService {
        @GetMapping(path = "/client/{id}")
        Client clientById(@PathVariable Integer id);
    }

    public static void main(String[] args) {
        SpringApplication.run(VoitureApplication.class, args);
    }

    @Bean
    CommandLineRunner initialiserBaseH2(VoitureRepository voitureRepository, ClientService clientService) {
        return args -> {
            Client c1 = clientService.clientById(1);
            Client c2 = clientService.clientById(2);

            System.out.println("**************************");
            System.out.println("Id est :" + c2.getId());
            System.out.println("Nom est :" + c2.getNom());
            System.out.println("**************************");

            System.out.println("**************************");
            System.out.println("Id est :" + c1.getId());
            System.out.println("Nom est :" + c1.getNom());
            System.out.println("Nom est :" + c1.getAge());
            System.out.println("**************************");

            voitureRepository.save(new Voiture(1, "Toyota", "A333", "Corolla", 1, c1));
			voitureRepository.save(new Voiture(2, "Hyundai", "BY78", "Hamrita", 2, c2));

        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
