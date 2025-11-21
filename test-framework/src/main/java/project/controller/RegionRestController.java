package project.controller;

import project.entity.Region;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/regions")
public class RegionRestController {

    @GetMapping
    public List<Region> getAllRegions() {
        // Filtrer pour ne pas renvoyer les régions "supprimées"
        
        // TODO: Vérifier pourquoi certaines régions ne sont pas correctement filtrées
        return regionRepository.findAll().stream()
                               .filter(region -> !region.isDeleted())
                               .collect(Collectors.toList());
    }

    // TODO: Ajouter la gestion des erreurs pour les cas où une région n'est pas trouvée
    @GetMapping("/{id}")
    public Region getRegionById(@PathVariable Long id) {
        return regionRepository.findById(id)
                               .orElseThrow(() -> 
                                   new ResourceNotFoundException("Region not found with id " + id));
    }
    // TODO: Penser à ajouter des tests unitaires supplémentaires pour la validation des JSON
    // ... autres méthodes ...
}