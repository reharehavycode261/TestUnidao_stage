package project.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import project.entity.Region;
import project.repository.RegionRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class DashboardServiceTest {

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private DashboardService dashboardService;

    @BeforeEach
    void setUp() {
        // This method can be used to set up any common test data or configurations
    }

    @Test
    void testRetrieveDashboardData_ReturnsAllRegions() {
        // Arrange
        Region region1 = new Region();
        Region region2 = new Region();
        List<Region> expectedRegions = Arrays.asList(region1, region2);
        when(regionRepository.findAll()).thenReturn(expectedRegions);

        // Act
        List<Region> actualRegions = dashboardService.retrieveDashboardData();

        // Assert
        assertEquals(expectedRegions.size(), actualRegions.size(), "The number of regions returned should match the expected number.");
        assertEquals(expectedRegions, actualRegions, "The regions returned should match the expected regions.");
    }

    @Test
    void testRetrieveDashboardData_ReturnsEmptyListWhenNoRegions() {
        // Arrange
        when(regionRepository.findAll()).thenReturn(Collections.emptyList());

        // Act
        List<Region> actualRegions = dashboardService.retrieveDashboardData();

        // Assert
        assertTrue(actualRegions.isEmpty(), "The returned list should be empty when there are no regions.");
    }
}