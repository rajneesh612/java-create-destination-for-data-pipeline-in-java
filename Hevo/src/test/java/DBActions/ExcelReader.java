package DBActions;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

   public static Row row;
    public static  Cell cell;

    public static String ExcelReader(int rowValue, int colValue) throws IOException {
        //Path of the excel file
        FileInputStream fs = new FileInputStream("E:\\HevoData.xlsx");
        //Creating a workbook
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
         row = sheet.getRow(rowValue);
         cell = row.getCell(colValue);
        return cell.getStringCellValue();
    }
}
