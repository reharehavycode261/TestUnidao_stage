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
        return territorieRepository.findAll().stream().filter(territorie -> !territorie.isDeleted()).collect(Collectors.toList());
    }

    @PostMapping("/delete/{id}")
    public void deleteTerritorie(@PathVariable Integer id) {
        Territorie territorie = territorieRepository.findById(id);
        if (territorie != null) {
            territorie.softDelete();
            territorieRepository.save(territorie);
        }
    }
}