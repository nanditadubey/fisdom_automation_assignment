package com.fisdom.dataReader;

/**
 * Created by nandita.dubey on 24/04/20.
 */
import com.fisdom.constants.ConfigConstants;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by nanditadubey on 23/09/19.
 */
public class ExcelReader {
    public static FileInputStream fis;
    public static XSSFWorkbook wb;
    public static XSSFSheet sheet;
    public static XSSFRow row;
    public static XSSFCell cell;

    public Object[][] getAddressSearchData(String sheettest) throws IOException {
        Object[][] data;
        FileInputStream fis = new FileInputStream(ConfigConstants.excelloc);
        wb = new XSSFWorkbook(fis);
        sheet = wb.getSheet(sheettest);
        row = sheet.getRow(0);
        boolean isCellBlank = false;
        int totalRows = sheet.getLastRowNum();
        int totalColumns = row.getLastCellNum();
        data = new Object[totalRows][totalColumns];

        for (int i = 1; i <= totalRows; i++) {
            for (int j = 0; j < totalColumns; j++) {
                try {
                    cell = sheet.getRow(i).getCell(j);
                } catch (NullPointerException e) {
                    isCellBlank = true;
                    data[i-1][j] = null;
                }

                if (!isCellBlank)
                    if (cell.getCellType() == CellType.STRING) {
                        data[i-1][j] =cell.getStringCellValue() ;
                        System.out.print(cell.getStringCellValue() + "--");
                    }
                    else if (cell.getCellType() == CellType.NUMERIC) {
                        data[i - 1][j] = (int) cell.getNumericCellValue();
                        System.out.print((int) cell.getNumericCellValue() + "--");
                    }
            }
        }

        return data;
    }
}
