package fr.unice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableCircuitBreaker
@SpringBootApplication
@EnableEurekaClient  // Active l'intégration avec Eureka
   // Active Hystrix pour la tolérance aux pannes
public class PraticienServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PraticienServiceApplication.class, args);
    }
}
