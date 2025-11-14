import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import mg.uniDao.exception.DaoException;
import mg.uniDao.core.Database;
import mg.uniDao.provider.GenericSqlProvider;
import com.google.gson.Gson;
import java.util.List;
import java.util.ArrayList;
import project.RegionController;

public class RegionControllerTest {

    private RegionController regionController;
    private Database mockDatabase;

    @BeforeEach
    public void setUp() throws DaoException {
        mockDatabase = Mockito.mock(Database.class);
        Mockito.when(GenericSqlProvider.get("database.json")).thenReturn(mockDatabase);
        regionController = new RegionController();
    }

    @AfterEach
    public void tearDown() {
        regionController = null;
    }

    @Test
    public void testSearchRegions_EmptyQuery() throws DaoException {
        List<Region> result = regionController.searchRegions("", "");
        Assertions.assertNotNull(result, "The result should not be null");
        Assertions.assertTrue(result.isEmpty(), "The result should be empty for an empty query");
    }

    @Test
    public void testSearchRegions_ValidQuery() throws DaoException {
        // Assuming some mock setup for valid query
        List<Region> result = regionController.searchRegions("validQuery", "filter");
        Assertions.assertNotNull(result, "The result should not be null");
        // Further assertions based on expected behavior
    }

    @Test
    public void testAutocomplete_EmptyTerm() {
        List<String> result = regionController.autocomplete("");
        Assertions.assertNotNull(result, "The result should not be null");
        Assertions.assertEquals(2, result.size(), "The result should contain two suggestions");
        Assertions.assertEquals("1", result.get(0), "The first suggestion should be '1'");
        Assertions.assertEquals("2", result.get(1), "The second suggestion should be '2'");
    }

    @Test
    public void testAutocomplete_ValidTerm() {
        List<String> result = regionController.autocomplete("test");
        Assertions.assertNotNull(result, "The result should not be null");
        Assertions.assertEquals(2, result.size(), "The result should contain two suggestions");
        Assertions.assertEquals("test1", result.get(0), "The first suggestion should be 'test1'");
        Assertions.assertEquals("test2", result.get(1), "The second suggestion should be 'test2'");
    }

    @Test
    public void testSaveQuery_ValidInput() {
        String result = regionController.saveQuery("query", "filter");
        Assertions.assertNotNull(result, "The result should not be null");
        List<String> expected = new ArrayList<>();
        expected.add("Requête: query, Filtre: filter");
        Assertions.assertEquals(new Gson().toJson(expected), result, "The saved query should match the expected format");
    }

    @Test
    public void testSaveQuery_MultipleQueries() {
        regionController.saveQuery("query1", "filter1");
        String result = regionController.saveQuery("query2", "filter2");
        Assertions.assertNotNull(result, "The result should not be null");
        List<String> expected = new ArrayList<>();
        expected.add("Requête: query1, Filtre: filter1");
        expected.add("Requête: query2, Filtre: filter2");
        Assertions.assertEquals(new Gson().toJson(expected), result, "The saved queries should match the expected format");
    }

    @Test
    public void testSearchRegions_ExceptionHandling() {
        try {
            Mockito.when(GenericSqlProvider.get("database.json")).thenThrow(new DaoException("Database error"));
            new RegionController();
            Assertions.fail("Expected DaoException to be thrown");
        } catch (DaoException e) {
            Assertions.assertEquals("Database error", e.getMessage(), "The exception message should match");
        }
    }
}