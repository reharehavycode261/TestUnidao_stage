package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import project.entity.Region;
import project.repository.RegionRepository;

@RestController
@RequestMapping("/regions")
public class RegionRestController {

    @Autowired
    private RegionRepository regionRepository;

    @GetMapping
    public Page<Region> getAllRegions(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sortBy", defaultValue = "regionId") String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
            @RequestParam(value = "filter", required = false) String filter) {
        Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        if (filter == null || filter.isEmpty()) {
            return regionRepository.findAll(pageable);
        } else {
            return regionRepository.findByRegionDescriptionContaining(filter, pageable);
        }
    }
}