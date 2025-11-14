import org.ff4j.FF4j;
import org.ff4j.core.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class MainTest {

    @Mock
    private FF4j ff4j;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @InjectMocks
    private Main main;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testMainWithNewSearchFeatureEnabled() {
        // Arrange
        when(ff4j.check("newSearchFeature")).thenReturn(true);
        when(ff4j.check("notificationsFeature")).thenReturn(false);

        // Act
        Main.main(new String[]{});

        // Assert
        assertTrue(outContent.toString().contains("La fonctionnalité de recherche avancée est activée!"), 
                   "Expected output to contain message about newSearchFeature being enabled.");
    }

    @Test
    public void testMainWithNotificationsFeatureEnabled() {
        // Arrange
        when(ff4j.check("newSearchFeature")).thenReturn(false);
        when(ff4j.check("notificationsFeature")).thenReturn(true);

        // Act
        Main.main(new String[]{});

        // Assert
        assertTrue(outContent.toString().contains("La fonctionnalité de notifications multi-canaux est activée!"), 
                   "Expected output to contain message about notificationsFeature being enabled.");
    }

    @Test
    public void testMainWithBothFeaturesEnabled() {
        // Arrange
        when(ff4j.check("newSearchFeature")).thenReturn(true);
        when(ff4j.check("notificationsFeature")).thenReturn(true);

        // Act
        Main.main(new String[]{});

        // Assert
        assertTrue(outContent.toString().contains("La fonctionnalité de recherche avancée est activée!"), 
                   "Expected output to contain message about newSearchFeature being enabled.");
        assertTrue(outContent.toString().contains("La fonctionnalité de notifications multi-canaux est activée!"), 
                   "Expected output to contain message about notificationsFeature being enabled.");
    }

    @Test
    public void testMainWithNoFeaturesEnabled() {
        // Arrange
        when(ff4j.check("newSearchFeature")).thenReturn(false);
        when(ff4j.check("notificationsFeature")).thenReturn(false);

        // Act
        Main.main(new String[]{});

        // Assert
        assertFalse(outContent.toString().contains("La fonctionnalité de recherche avancée est activée!"), 
                    "Expected output not to contain message about newSearchFeature being enabled.");
        assertFalse(outContent.toString().contains("La fonctionnalité de notifications multi-canaux est activée!"), 
                    "Expected output not to contain message about notificationsFeature being enabled.");
    }
}