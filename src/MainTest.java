import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import java.util.ResourceBundle;

public class MainTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainMethodWithFrenchLocale() {
        // Set up the expected output
        Locale locale = new Locale("fr", "FR");
        ResourceBundle messages = ResourceBundle.getBundle("MessagesBundle", locale);
        String expectedOutput = messages.getString("greetings") + System.lineSeparator();

        // Execute the main method
        Main.main(new String[]{});

        // Assert the output
        Assertions.assertEquals(expectedOutput, outputStreamCaptor.toString(), "The output should match the greetings message in French.");
    }

    @Test
    public void testMainMethodWithMissingResourceBundle() {
        // Temporarily change the default locale to a non-existent one
        Locale defaultLocale = Locale.getDefault();
        Locale.setDefault(new Locale("xx", "XX"));

        // Expect a MissingResourceException
        Assertions.assertThrows(java.util.MissingResourceException.class, () -> {
            Main.main(new String[]{});
        }, "A MissingResourceException should be thrown for a non-existent resource bundle.");

        // Reset the default locale
        Locale.setDefault(defaultLocale);
    }
}