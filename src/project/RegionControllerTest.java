import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import mg.uniDao.core.Database;
import mg.uniDao.exception.DaoException;
import java.util.Locale;
import java.util.ResourceBundle;
import project.RegionController;

public class RegionControllerTest {
    
    private RegionController regionController;
    private Database mockDatabase;

    @BeforeEach
    public void setUp() throws DaoException {
        // Mocking the Database class
        mockDatabase = Mockito.mock(Database.class);
        // Injecting the mock into the RegionController using reflection
        regionController = new RegionController();
        // Using reflection to set the private database field
        java.lang.reflect.Field databaseField = RegionController.class.getDeclaredField("database");
        databaseField.setAccessible(true);
        databaseField.set(regionController, mockDatabase);
    }

    @AfterEach
    public void tearDown() {
        regionController = null;
    }

    @Test
    public void testSetLocaleWithValidLocale() {
        Locale locale = new Locale("fr", "FR");
        regionController.setLocale(locale);
        ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", locale);
        Assertions.assertEquals(messages.getLocale(), regionController.getLocalizedText("welcome.message"));
    }

    @Test
    public void testGetLocalizedTextWithValidKey() {
        Locale locale = new Locale("en", "US");
        regionController.setLocale(locale);
        String expectedMessage = ResourceBundle.getBundle("MessagesBundle", locale).getString("welcome.message");
        Assertions.assertEquals(expectedMessage, regionController.getLocalizedText("welcome.message"));
    }

    @Test
    public void testGetLocalizedTextWithInvalidKey() {
        Locale locale = new Locale("en", "US");
        regionController.setLocale(locale);
        Assertions.assertThrows(java.util.MissingResourceException.class, () -> {
            regionController.getLocalizedText("invalid.key");
        });
    }

    @Test
    public void testSetLocaleWithNullLocale() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            regionController.setLocale(null);
        });
    }
}