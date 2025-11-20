package project.controller;

import project.entity.Region;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/regions")
public class RegionRestController {

    @GetMapping
    public List<Region> getAllRegions() {
        return regionRepository.findAll()
                .stream()
                .filter(region -> !region.isDeleted())
                .collect(Collectors.toList());
    }

    @PostMapping
    public Region createRegion(@Valid @RequestBody Region region) {
        try {
            return regionRepository.save(region);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid data", e);
        }
    }
}