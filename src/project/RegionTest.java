import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import java.util.List;
import java.util.ArrayList;

public class RegionTest {

    private Region region;

    @BeforeEach
    public void setUp() {
        region = new Region();
    }

    @Test
    public void testDefaultConstructorInitializesNotificationChannels() {
        List<String> expectedChannels = List.of("in-app", "email");
        Assertions.assertEquals(expectedChannels, region.getNotificationChannels(), 
            "Default constructor should initialize notification channels with 'in-app' and 'email'");
    }

    @Test
    public void testGetId() {
        region.setId(5);
        Assertions.assertEquals(5, region.getId(), "getId should return the correct id");
    }

    @Test
    public void testSetId() {
        region.setId(10);
        Assertions.assertEquals(10, region.getId(), "setId should correctly set the id");
    }

    @Test
    public void testGetName() {
        region.setName("Test Region");
        Assertions.assertEquals("Test Region", region.getName(), "getName should return the correct name");
    }

    @Test
    public void testSetName() {
        region.setName("New Region");
        Assertions.assertEquals("New Region", region.getName(), "setName should correctly set the name");
    }

    @Test
    public void testGetNotificationChannels() {
        List<String> channels = region.getNotificationChannels();
        Assertions.assertNotNull(channels, "getNotificationChannels should not return null");
        Assertions.assertTrue(channels.contains("in-app"), "Default channels should include 'in-app'");
        Assertions.assertTrue(channels.contains("email"), "Default channels should include 'email'");
    }

    @Test
    public void testSetNotificationChannels() {
        List<String> newChannels = new ArrayList<>();
        newChannels.add("push");
        region.setNotificationChannels(newChannels);
        Assertions.assertEquals(newChannels, region.getNotificationChannels(), 
            "setNotificationChannels should correctly set the notification channels");
    }

    @Test
    public void testSetNotificationChannelsWithNull() {
        region.setNotificationChannels(null);
        Assertions.assertNull(region.getNotificationChannels(), 
            "setNotificationChannels should allow setting the channels to null");
    }
}