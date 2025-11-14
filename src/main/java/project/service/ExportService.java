package project.service;

import org.springframework.stereotype.Service;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.IOException;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ExportService {

    public void exportDataToCSV(Writer writer, List<Object[]> data) throws IOException {
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("ID", "Description", "Value"))) {
            data.forEach(record -> {
                try {
                    csvPrinter.printRecord(record);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}