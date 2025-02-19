package fr.unice.controller;

import fr.unice.model.Praticien;

import fr.unice.service.PraticienService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/praticiens")
@RequiredArgsConstructor
@Api(value = "API pour gérer les praticiens", tags = "Praticiens")
public class PraticienController {

    private final PraticienService praticienService;

    @GetMapping
    @ApiOperation(value = "Obtenir tous les praticiens", notes = "Retourne la liste de tous les praticiens disponibles.")
    public List<Praticien> getAllPraticiens() {
        return praticienService.getAllPraticiens();
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Obtenir un praticien par ID", notes = "Retourne un praticien spécifique selon son ID.")
    public Optional<Praticien> getPraticienById(@PathVariable Long id) {
        return praticienService.getPraticienById(id);
    }

    @PostMapping
    @ApiOperation(value = "Ajouter un praticien", notes = "Ajoute un nouveau praticien.")
    public Praticien addPraticien(@RequestBody Praticien praticien) {
        return praticienService.addPraticien(praticien);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Supprimer un praticien", notes = "Supprime un praticien par son ID.")
    public boolean deletePraticien(@PathVariable Long id) {
        return praticienService.deletePraticien(id);
    }
}
