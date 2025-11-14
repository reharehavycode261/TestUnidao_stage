import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import mg.uniDao.core.Database;
import mg.uniDao.core.Service;
import mg.uniDao.exception.DaoException;
import mg.uniDao.provider.GenericSqlProvider;
import project.Region;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainMethodOutputsTest() throws DaoException {
        // Mocking Database and Service
        Database mockDatabase = Mockito.mock(Database.class);
        Service mockService = Mockito.mock(Service.class);

        // Mocking the behavior of GenericSqlProvider
        Mockito.when(GenericSqlProvider.get("database.json")).thenReturn(mockDatabase);
        Mockito.when(mockDatabase.connect("TEST", true)).thenReturn(mockService);

        // Mocking the updateById method
        Mockito.doNothing().when(mockDatabase).updateById(Mockito.eq(mockService), Mockito.any(Region.class), Mockito.eq(48));

        // Mocking the close method
        Mockito.doNothing().when(mockService).close();

        // Execute main method
        Main.main(new String[]{});

        // Verify the output
        Assertions.assertEquals("test\n", outContent.toString(), "Main method should output 'test' to the console.");

        // Verify interactions
        Mockito.verify(mockDatabase).connect("TEST", true);
        Mockito.verify(mockDatabase).updateById(Mockito.eq(mockService), Mockito.any(Region.class), Mockito.eq(48));
        Mockito.verify(mockService).close();
    }

    @Test
    public void testMainMethodThrowsDaoException() {
        // Mocking Database to throw DaoException
        Database mockDatabase = Mockito.mock(Database.class);

        // Mocking the behavior of GenericSqlProvider
        Mockito.when(GenericSqlProvider.get("database.json")).thenReturn(mockDatabase);

        // Simulate DaoException when connect is called
        try {
            Mockito.when(mockDatabase.connect("TEST", true)).thenThrow(new DaoException("Connection failed"));
            Main.main(new String[]{});
            Assertions.fail("Expected DaoException to be thrown");
        } catch (DaoException e) {
            Assertions.assertEquals("Connection failed", e.getMessage(), "DaoException message should be 'Connection failed'");
        }
    }
}