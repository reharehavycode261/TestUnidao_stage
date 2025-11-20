package project.controller;

import project.entity.Territorie;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/territories")
public class TerritorieRestController {

    @GetMapping
    public List<Territorie> getAllTerritories() {
        return territorieRepository.findAll()
                .stream()
                .filter(territorie -> !territorie.isDeleted())
                .collect(Collectors.toList());
    }

    @PostMapping
    public Territorie createTerritorie(@Valid @RequestBody Territorie territorie) {
        try {
            return territorieRepository.save(territorie);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data", e);
        }
    }
}