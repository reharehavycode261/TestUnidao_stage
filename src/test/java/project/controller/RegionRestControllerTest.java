package project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import project.entity.Region;
import project.repository.RegionRepository;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RegionRestControllerTest {

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private RegionRestController regionRestController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllRegionsPagination() {
        PageRequest pageable = PageRequest.of(0, 10, Sort.by("regionId").ascending());
        Page<Region> dummyPage = new PageImpl<>(Collections.emptyList(), pageable, 0);
        
        when(regionRepository.findAll(pageable)).thenReturn(dummyPage);

        Page<Region> result = regionRestController.getAllRegions(0, 10, "regionId", "asc", null);
        assertEquals(0, result.getTotalElements());
    }
}