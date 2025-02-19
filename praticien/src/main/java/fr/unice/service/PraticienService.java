package fr.unice.service;

import fr.unice.model.Praticien;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PraticienService {
    private final List<Praticien> praticiens = new ArrayList<>();

    public PraticienService() {
        praticiens.add(new Praticien(1L, "Dr. Dupont", "Cardiologue", "dupont@mail.com", "0601020304"));
        praticiens.add(new Praticien(2L, "Dr. Martin", "Dentiste", "martin@mail.com", "0611223344"));
    }


    public List<Praticien> getAllPraticiens() {
        return praticiens;
    }

    public List<Praticien> fallbackGetAllPraticiens() {
        log.warn("Service indisponible. Renvoyant une liste vide.");
        return null;
        //return List.of(new Praticien(0L, "Indisponible", "Aucune", "n/a", "n/a"));
    }

    public Optional<Praticien> getPraticienById(Long id) {
        return praticiens.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Praticien addPraticien(Praticien praticien) {
        praticien.setId((long) (praticiens.size() + 1));
        praticiens.add(praticien);
        log.info("Ajout d'un nouveau praticien : {}", praticien);
        return praticien;
    }

    public boolean deletePraticien(Long id) {
        return praticiens.removeIf(p -> p.getId().equals(id));
    }
}
