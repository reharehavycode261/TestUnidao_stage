package project.controller;

import project.entity.Territorie;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Contrôleur REST pour gérer les opérations CRUD sur les entités Territorie.
 */
@RestController
@RequestMapping("/territories")
public class TerritorieRestController {

    /**
     * Récupère la liste de tous les territoires non supprimés.
     * 
     * @return La liste des territoires
     */
    @GetMapping
    public List<Territorie> getAllTerritories() {
        // Filtrer pour ne pas renvoyer les territoires "supprimés"
        return territorieRepository.findAll().stream().filter(territorie -> !territorie.isDeleted()).collect(Collectors.toList());
    }

    // Des méthodes supplémentaires avec documentation peuvent être ajoutées ici
}