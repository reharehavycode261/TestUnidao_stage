package project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import project.entity.Region;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.ArrayList;

@RestController
public class RegionRestController {

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=regions.xlsx");

        List<Region> regions = getRegions();  // Assuming a method to fetch regions

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Regions");
        Row headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue("Region ID");
        headerRow.createCell(1).setCellValue("Region Description");

        int rowCount = 1;

        for (Region region : regions) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(region.getRegionId());
            row.createCell(1).setCellValue(region.getRegionDescription());
        }

        try (OutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
        } finally {
            workbook.close();
        }
    }

    @GetMapping("/export/csv")
    public void exportToCSV(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=regions.csv");

        List<Region> regions = getRegions();  // Assuming a method to fetch regions

        ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(),
                                                    CsvPreference.STANDARD_PREFERENCE);

        String[] header = {"RegionId", "RegionDescription"};
        csvWriter.writeHeader(header);

        for (Region region : regions) {
            csvWriter.write(region, header);
        }

        csvWriter.close();
    }

    // Mock method to fetch regions
    private List<Region> getRegions() {
        List<Region> regions = new ArrayList<>();
        regions.add(new Region(1, "North"));
        regions.add(new Region(2, "South"));
        return regions;
    }
}