package project.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.StringWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.ArrayList;

public class ExportServiceTest {

    private ExportService exportService;
    private Writer writer;
    private List<Object[]> data;

    @BeforeEach
    public void setUp() {
        exportService = new ExportService();
        writer = new StringWriter();
        data = new ArrayList<>();
    }

    @AfterEach
    public void tearDown() throws IOException {
        writer.close();
    }

    @Test
    public void testExportDataToCSV_withValidData() throws IOException {
        // Arrange
        data.add(new Object[]{1, "Description 1", 100});
        data.add(new Object[]{2, "Description 2", 200});

        // Act
        exportService.exportDataToCSV(writer, data);
        String result = writer.toString();

        // Assert
        Assertions.assertTrue(result.contains("ID,Description,Value"), "CSV should contain header");
        Assertions.assertTrue(result.contains("1,Description 1,100"), "CSV should contain first record");
        Assertions.assertTrue(result.contains("2,Description 2,200"), "CSV should contain second record");
    }

    @Test
    public void testExportDataToCSV_withEmptyData() throws IOException {
        // Arrange
        // No data added to the list

        // Act
        exportService.exportDataToCSV(writer, data);
        String result = writer.toString();

        // Assert
        Assertions.assertTrue(result.contains("ID,Description,Value"), "CSV should contain header even if data is empty");
        Assertions.assertEquals("ID,Description,Value\n", result, "CSV should only contain header for empty data");
    }

    @Test
    public void testExportDataToCSV_withIOException() throws IOException {
        // Arrange
        Writer mockWriter = Mockito.mock(Writer.class);
        Mockito.doThrow(new IOException("Mock IO Exception")).when(mockWriter).write(Mockito.anyString());
        data.add(new Object[]{1, "Description 1", 100});

        // Act & Assert
        Assertions.assertThrows(IOException.class, () -> {
            exportService.exportDataToCSV(mockWriter, data);
        }, "Should throw IOException when writer fails");
    }

    @Test
    public void testExportDataToCSV_withNullWriter() {
        // Arrange
        // Writer is null

        // Act & Assert
        Assertions.assertThrows(NullPointerException.class, () -> {
            exportService.exportDataToCSV(null, data);
        }, "Should throw NullPointerException when writer is null");
    }

    @Test
    public void testExportDataToCSV_withNullData() throws IOException {
        // Arrange
        // Data is null

        // Act
        exportService.exportDataToCSV(writer, null);
        String result = writer.toString();

        // Assert
        Assertions.assertTrue(result.contains("ID,Description,Value"), "CSV should contain header even if data is null");
    }
}