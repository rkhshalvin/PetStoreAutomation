
package api.utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUsimple {

    private final String path;

    public XLUsimple(String path) {
        this.path = path;
    }

    // Get the number of rows in a sheet
    public int getRowCount(String sheetName) throws IOException {

        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                        "Sheet not found: " + sheetName);
            }

            return sheet.getLastRowNum() + 1;
        }
    }

    // Get the number of cells in a particular row
    public int getCellCount(String sheetName, int rowNum)
            throws IOException {

        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                        "Sheet not found: " + sheetName);
            }

            Row row = sheet.getRow(rowNum);

            if (row == null) {
                return 0;
            }

            return row.getLastCellNum() < 0
                    ? 0 : row.getLastCellNum();
        }
    }

    // Read data from a specific cell
    public String getCellData(
            String sheetName, int rowNum, int colNum)
            throws IOException {

        try (FileInputStream fi = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fi)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                        "Sheet not found: " + sheetName);
            }

            Row row = sheet.getRow(rowNum);

            if (row == null) {
                return "";
            }

            Cell cell = row.getCell(
                    colNum, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

            if (cell == null) {
                return "";
            }

            DataFormatter formatter = new DataFormatter();

            return formatter.formatCellValue(cell);
        }
    }
}
