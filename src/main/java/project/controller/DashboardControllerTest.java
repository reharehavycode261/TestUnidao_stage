package project.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import project.service.DashboardService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class DashboardControllerTest {

    @Mock
    private DashboardService dashboardService;

    @InjectMocks
    private DashboardController dashboardController;

    @BeforeEach
    public void setUp() {
        // Reset mocks before each test if necessary
        Mockito.reset(dashboardService);
    }

    @Test
    public void testGetDashboardData_Success() {
        // Arrange
        List<Object> mockData = Arrays.asList(new Object(), new Object());
        when(dashboardService.retrieveDashboardData()).thenReturn(mockData);

        // Act
        List<Object> result = dashboardController.getDashboardData();

        // Assert
        assertEquals(mockData, result, "The returned dashboard data should match the mock data.");
    }

    @Test
    public void testGetDashboardData_EmptyList() {
        // Arrange
        when(dashboardService.retrieveDashboardData()).thenReturn(Collections.emptyList());

        // Act
        List<Object> result = dashboardController.getDashboardData();

        // Assert
        assertEquals(0, result.size(), "The returned dashboard data should be an empty list.");
    }

    @Test
    public void testGetDashboardData_ServiceThrowsException() {
        // Arrange
        when(dashboardService.retrieveDashboardData()).thenThrow(new RuntimeException("Service error"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            dashboardController.getDashboardData();
        });

        assertEquals("Service error", exception.getMessage(), "The exception message should match the service error.");
    }
}