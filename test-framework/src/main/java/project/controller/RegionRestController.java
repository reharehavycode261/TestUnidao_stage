package project.controller;

import project.entity.Region;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrôleur REST pour gérer les opérations CRUD sur les entités Region.
 */
@RestController
@RequestMapping("/regions")
public class RegionRestController {

    /**
     * Récupère la liste de toutes les régions non supprimées.
     * 
     * @return La liste des régions
     */
    @GetMapping
    public List<Region> getAllRegions() {
        // Filtrer pour ne pas renvoyer les régions "supprimées"
        return regionRepository.findAll().stream().filter(region -> !region.isDeleted()).collect(Collectors.toList());
    }

    // Des méthodes supplémentaires avec documentation peuvent être ajoutées ici
}