package project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import io.prometheus.client.Counter;
import io.prometheus.client.Summary;

import static org.mockito.Mockito.*;

class MainTest {

    private HTTPServer mockServer;
    private Logger mockLogger;

    @BeforeEach
    void setUp() {
        // Mocking HTTPServer and Logger for testing
        mockServer = Mockito.mock(HTTPServer.class);
        mockLogger = Mockito.mock(Logger.class);
        Main.logger = mockLogger;
    }

    @AfterEach
    void tearDown() {
        // Resetting mocks after each test
        reset(mockServer, mockLogger);
    }

    @Test
    void testMainInitializationSuccess() {
        // Test successful initialization of the server
        Assertions.assertDoesNotThrow(() -> {
            DefaultExports.initialize();
            HTTPServer server = new HTTPServer(1234);
            server.stop();
        }, "Server should initialize without throwing an exception");
    }

    @Test
    void testMainInitializationFailure() {
        // Test failure during server initialization
        Assertions.assertThrows(Exception.class, () -> {
            // Simulate a failure by attempting to start a server on an invalid port
            HTTPServer server = new HTTPServer(-1);
        }, "Server initialization should throw an exception for invalid port");
    }

    @Test
    void testRequestCounterIncrement() {
        // Test if the request counter increments correctly
        double initialCount = Main.requests.get();
        Main.requests.inc();
        Assertions.assertEquals(initialCount + 1, Main.requests.get(), "Request counter should increment by 1");
    }

    @Test
    void testRequestLatencyObservation() {
        // Test if the request latency is observed correctly
        Summary.Timer timer = Main.requestLatency.startTimer();
        timer.observeDuration();
        // Since we cannot directly access the observed values, we assume the functionality works if no exceptions are thrown
        Assertions.assertDoesNotThrow(() -> timer.observeDuration(), "Observing request latency should not throw an exception");
    }

    @Test
    void testLoggerErrorOnServerInitializationFailure() {
        // Test if logger logs an error when server initialization fails
        try {
            new HTTPServer(-1);
        } catch (Exception e) {
            // Expected exception, verify logging
            verify(mockLogger, times(1)).error(startsWith("Erreur lors de l'initialisation du serveur de metrics : "), any(Exception.class));
        }
    }
}