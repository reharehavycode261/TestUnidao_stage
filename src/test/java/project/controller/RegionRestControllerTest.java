package project.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import project.entity.Region;
import project.repository.RegionRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

class RegionRestControllerTest {

    @InjectMocks
    RegionRestController regionRestController;

    @Mock
    RegionRepository regionRepository;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllRegions() {
        List<Region> mockRegions = new ArrayList<>();
        Region region = new Region();
        region.setId(1L);
        region.setDeleted(false);
        mockRegions.add(region);

        when(regionRepository.findAll()).thenReturn(mockRegions);

        List<Region> result = regionRestController.getAllRegions();
        assertEquals(1, result.size());
        assertEquals(region, result.get(0));
    }
}