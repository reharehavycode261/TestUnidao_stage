package project.controller;

import project.entity.Territorie;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/territories")
public class TerritorieRestController {

    @GetMapping
    public List<Territorie> getAllTerritories() {
        // Filtrer pour ne pas renvoyer les territoires "supprimés"
        
        // TODO: Vérifier pourquoi certains territoires ne sont pas correctement filtrés
        return territorieRepository.findAll().stream()
                                   .filter(territorie -> !territorie.isDeleted())
                                   .collect(Collectors.toList());
    }

    // TODO: Ajouter la gestion des erreurs pour les cas où un territoire n'est pas trouvé
    @GetMapping("/{id}")
    public Territorie getTerritorieById(@PathVariable Long id) {
        return territorieRepository.findById(id)
                                   .orElseThrow(() -> 
                                       new ResourceNotFoundException("Territorie not found with id " + id));
    }
    // TODO: Penser à ajouter des tests unitaires supplémentaires pour la validation des JSON
    // ... autres méthodes ...
}