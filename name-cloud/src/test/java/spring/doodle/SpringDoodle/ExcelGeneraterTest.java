package spring.doodle.SpringDoodle;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExcelGeneraterTest {

    private String INPUT_FILE = "/Users/jameszhou/Documents/Projects/GreatDane/test.csv";
    private String OUTPUT_FILE = "/Users/jameszhou/Documents/Projects/GreatDane/test_output.xlsx";

    @Test
    public void excelGen() throws IOException {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(INPUT_FILE));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        }

        buildExcel(records);

    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }

    private void buildExcel(List<List<String>> data) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Test Sheet");

        // create header style
        CellStyle headerStyle = workbook.createCellStyle();
        // style, background color: light blue
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
        // style, ?
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // style, font
        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 16);
        font.setBold(true);
        headerStyle.setFont(font);

        // create content style
        CellStyle contentStyle = workbook.createCellStyle();
        contentStyle.setWrapText(true);

        createRow(sheet, headerStyle, 0, data.get(0));

        for (int i = 1; i < data.size() ; i++ ) {
            createRow(sheet, contentStyle, i, data.get(i));
        }

        // fix the first row
        sheet.createFreezePane(0, 1);

        // add filter
        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, data.get(0).size() - 1));
        // auto resize all columns
        for (int i = 0 ; i < data.get(0).size(); i++) {
            sheet.autoSizeColumn(i);
        }

        // write to file
        FileOutputStream outputStream = new FileOutputStream(OUTPUT_FILE);
        workbook.write(outputStream);
        workbook.close();
    }

    private Row createRow(Sheet sheet, CellStyle style, int rowNum, List<String> rowData) {
        Row row = sheet.createRow(rowNum);
        for (int i = 0 ; i < rowData.size() ; i++) {
            createCell(row, style, i, rowData.get(i));
        }

        return row;
    }
    private Cell createCell(Row row, CellStyle style, int colNum, String data) {
        Cell cell = row.createCell(colNum);
        cell.setCellValue(data);
        cell.setCellStyle(style);
        return cell;
    }
}
