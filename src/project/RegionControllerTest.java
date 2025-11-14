import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import mg.uniDao.exception.DaoException;
import mg.uniDao.core.Service;
import mg.uniDao.core.Database;
import mg.uniDao.provider.GenericSqlProvider;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class RegionControllerTest {

    private RegionController regionController;
    private Database mockDatabase;
    private Service mockService;

    @BeforeEach
    public void setUp() throws DaoException {
        mockDatabase = Mockito.mock(Database.class);
        mockService = Mockito.mock(Service.class);
        Mockito.when(mockDatabase.connect(Mockito.anyString(), Mockito.anyBoolean())).thenReturn(mockService);
        Mockito.when(mockService.findAll(Mockito.any(Region.class))).thenReturn(getMockRegions());
        Mockito.doNothing().when(mockService).closeConnection();

        // Use reflection to inject the mock database
        regionController = new RegionController();
        java.lang.reflect.Field databaseField = RegionController.class.getDeclaredField("database");
        databaseField.setAccessible(true);
        databaseField.set(regionController, mockDatabase);
    }

    @AfterEach
    public void tearDown() {
        regionController = null;
    }

    @Test
    public void testGetRegionsSortedByNameAscending() throws DaoException {
        List<Region> regions = regionController.getRegions("name", false, 0, 10);
        Assertions.assertEquals(3, regions.size(), "Should return 3 regions");
        Assertions.assertEquals("Alpha", regions.get(0).getName(), "First region should be Alpha");
        Assertions.assertEquals("Beta", regions.get(1).getName(), "Second region should be Beta");
        Assertions.assertEquals("Gamma", regions.get(2).getName(), "Third region should be Gamma");
    }

    @Test
    public void testGetRegionsSortedByNameDescending() throws DaoException {
        List<Region> regions = regionController.getRegions("name", true, 0, 10);
        Assertions.assertEquals(3, regions.size(), "Should return 3 regions");
        Assertions.assertEquals("Gamma", regions.get(0).getName(), "First region should be Gamma");
        Assertions.assertEquals("Beta", regions.get(1).getName(), "Second region should be Beta");
        Assertions.assertEquals("Alpha", regions.get(2).getName(), "Third region should be Alpha");
    }

    @Test
    public void testGetRegionsPagination() throws DaoException {
        List<Region> regions = regionController.getRegions(null, false, 0, 2);
        Assertions.assertEquals(2, regions.size(), "Should return 2 regions for the first page");
        Assertions.assertEquals("Alpha", regions.get(0).getName(), "First region should be Alpha");
        Assertions.assertEquals("Beta", regions.get(1).getName(), "Second region should be Beta");

        regions = regionController.getRegions(null, false, 1, 2);
        Assertions.assertEquals(1, regions.size(), "Should return 1 region for the second page");
        Assertions.assertEquals("Gamma", regions.get(0).getName(), "First region on second page should be Gamma");
    }

    @Test
    public void testGetRegionsInvalidPage() throws DaoException {
        List<Region> regions = regionController.getRegions(null, false, 10, 2);
        Assertions.assertEquals(0, regions.size(), "Should return 0 regions for an out of bounds page");
    }

    @Test
    public void testGetRegionsInvalidSortBy() throws DaoException {
        List<Region> regions = regionController.getRegions("invalid", false, 0, 10);
        Assertions.assertEquals(3, regions.size(), "Should return 3 regions when sortBy is invalid");
    }

    @Test
    public void testGetRegionsDaoException() throws DaoException {
        Mockito.when(mockService.findAll(Mockito.any(Region.class))).thenThrow(new DaoException("Database error"));
        Assertions.assertThrows(DaoException.class, () -> {
            regionController.getRegions(null, false, 0, 10);
        }, "Should throw DaoException when database access fails");
    }

    private List<Region> getMockRegions() {
        Region region1 = new Region();
        region1.setName("Alpha");
        Region region2 = new Region();
        region2.setName("Beta");
        Region region3 = new Region();
        region3.setName("Gamma");
        return Arrays.asList(region1, region2, region3);
    }
}