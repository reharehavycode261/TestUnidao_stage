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
        return regionRepository.findAll().stream().filter(region -> !region.isDeleted()).collect(Collectors.toList());
    }

    @PostMapping("/delete/{id}")
    public void deleteRegion(@PathVariable Integer id) {
        Region region = regionRepository.findById(id);
        if (region != null) {
            region.softDelete();
            regionRepository.save(region);
        }
    }
}