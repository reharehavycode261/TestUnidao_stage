import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import mg.uniDao.core.Database;
import mg.uniDao.core.Service;
import mg.uniDao.exception.DaoException;
import mg.uniDao.provider.GenericSqlProvider;
import project.notification.NotificationService;

public class MainTest {

    private Database mockDatabase;
    private Service mockService;
    private NotificationService mockNotificationService;
    private Region mockRegion;

    @BeforeEach
    public void setUp() {
        mockDatabase = Mockito.mock(Database.class);
        mockService = Mockito.mock(Service.class);
        mockNotificationService = Mockito.mock(NotificationService.class);
        mockRegion = Mockito.mock(Region.class);
    }

    @AfterEach
    public void tearDown() {
        Mockito.reset(mockDatabase, mockService, mockNotificationService, mockRegion);
    }

    @Test
    public void testMainSuccess() throws DaoException {
        // Arrange
        Mockito.when(GenericSqlProvider.get("database.json")).thenReturn(mockDatabase);
        Mockito.when(mockDatabase.connect("TEST", true)).thenReturn(mockService);

        // Act
        Main.main(new String[]{});

        // Assert
        Mockito.verify(mockDatabase).connect("TEST", true);
        Mockito.verify(mockDatabase).updateById(mockService, mockRegion, 48);
        Mockito.verify(mockNotificationService).sendNotifications();
        Mockito.verify(mockService).close();
    }

    @Test
    public void testMainDatabaseConnectionFailure() throws DaoException {
        // Arrange
        Mockito.when(GenericSqlProvider.get("database.json")).thenReturn(mockDatabase);
        Mockito.when(mockDatabase.connect("TEST", true)).thenThrow(new DaoException("Connection failed"));

        // Act & Assert
        Assertions.assertThrows(DaoException.class, () -> {
            Main.main(new String[]{});
        });
    }

    @Test
    public void testMainUpdateByIdFailure() throws DaoException {
        // Arrange
        Mockito.when(GenericSqlProvider.get("database.json")).thenReturn(mockDatabase);
        Mockito.when(mockDatabase.connect("TEST", true)).thenReturn(mockService);
        Mockito.doThrow(new DaoException("Update failed")).when(mockDatabase).updateById(mockService, mockRegion, 48);

        // Act & Assert
        Assertions.assertThrows(DaoException.class, () -> {
            Main.main(new String[]{});
        });
    }

    @Test
    public void testMainNotificationFailure() throws DaoException {
        // Arrange
        Mockito.when(GenericSqlProvider.get("database.json")).thenReturn(mockDatabase);
        Mockito.when(mockDatabase.connect("TEST", true)).thenReturn(mockService);
        Mockito.doThrow(new RuntimeException("Notification failed")).when(mockNotificationService).sendNotifications();

        // Act & Assert
        Assertions.assertThrows(RuntimeException.class, () -> {
            Main.main(new String[]{});
        });
    }

    @Test
    public void testMainServiceCloseFailure() throws DaoException {
        // Arrange
        Mockito.when(GenericSqlProvider.get("database.json")).thenReturn(mockDatabase);
        Mockito.when(mockDatabase.connect("TEST", true)).thenReturn(mockService);
        Mockito.doThrow(new RuntimeException("Close failed")).when(mockService).close();

        // Act
        Main.main(new String[]{});

        // Assert
        Mockito.verify(mockService).close();
    }
}