package fr.unice.service;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import fr.unice.model.Praticien;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class PraticienService {

    private final List<Praticien> praticiens = new ArrayList<>();

    public PraticienService() {
        praticiens.add(new Praticien(1L, "Dr. Dupont", "Cardiologue", "dupont@mail.com", "0601020304"));
        praticiens.add(new Praticien(2L, "Dr. Martin", "Dentiste", "martin@mail.com", "0611223344"));
    }

    @HystrixCommand(fallbackMethod = "fallbackGetAllPraticiens")
    public List<Praticien> getAllPraticiens() {
        simulateRateLimiting();
        return praticiens;
    }

    @HystrixCommand(fallbackMethod = "fallbackGetPraticienById")
    public Optional<Praticien> getPraticienById(Long id) {
        simulateRateLimiting();
        return praticiens.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    @HystrixCommand(fallbackMethod = "fallbackAddPraticien")
    public Praticien addPraticien(Praticien praticien) {
        simulateRateLimiting();
        praticien.setId((long) (praticiens.size() + 1));
        praticiens.add(praticien);
        log.info("Ajout d'un nouveau praticien : {}", praticien);
        return praticien;
    }

    @HystrixCommand(fallbackMethod = "fallbackDeletePraticien")
    public boolean deletePraticien(Long id) {
        simulateRateLimiting();
        return praticiens.removeIf(p -> p.getId().equals(id));
    }

    private void simulateRateLimiting() {
        // Simule un retard pour tester les seuils ou un grand nombre de requêtes.
        // Par exemple, un appel lent ici pourrait provoquer un circuit breaker si
        // on dépasse les seuils configurés pour Hystrix.
        try {
            TimeUnit.MILLISECONDS.sleep(10); // Simuler un délai.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public List<Praticien> fallbackGetAllPraticiens() {
        log.warn("Circuit breaker déclenché pour getAllPraticiens.");
        return List.of(new Praticien(0L, "Indisponible", "Aucune spécialité", "n/a", "n/a"));
    }

    public Optional<Praticien> fallbackGetPraticienById(Long id) {
        log.warn("Circuit breaker déclenché pour getPraticienById.");
        return Optional.empty();
    }

    public Praticien fallbackAddPraticien(Praticien praticien) {
        log.warn("Circuit breaker déclenché pour addPraticien.");
        return new Praticien(0L, "Indisponible", "Aucune spécialité", "n/a", "n/a");
    }

    public boolean fallbackDeletePraticien(Long id) {
        log.warn("Circuit breaker déclenché pour deletePraticien.");
        return false;
    }
}
