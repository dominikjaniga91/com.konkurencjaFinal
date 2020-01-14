package model;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.Map;
import java.util.Set;

public class Excel {

    /**
     * This class is responsible for creating new xslx file and saving provided data.
     *
     * @return a new XSSFWorkbook spreadsheet
     */


    static XSSFWorkbook createNewXlsxFile() {

        System.out.println("Creating new XSSFWorkbook..");
        XSSFWorkbook xslxSpreadsheet = new XSSFWorkbook();
        xslxSpreadsheet.createSheet("Brandy");

        return xslxSpreadsheet;
    }

    /**
     *
     * @param data a HashMap with data from html code
     * @param xslxSpreadsheet an XSSFWorkbook spreadsheet
     * @return an XSSFWorkbook spreadsheet
     */

    static void writeToExcel(Map<Integer, Object[]> data, XSSFWorkbook xslxSpreadsheet) {

            System.out.println("Starting writing on XLSX file...");

            XSSFSheet mySheet = xslxSpreadsheet.getSheetAt(0);

            Set<Integer> newRows = data.keySet();

            int rowNumber = mySheet.getLastRowNum() + 1;

            for (Integer key : newRows) {

                Row row = mySheet.createRow(rowNumber++);
                Object[] objAtt = data.get(key);
                int cellNumber = 0;
                for (Object obj : objAtt) {
                    Cell cell = row.createCell(cellNumber++);
                    cell.setCellValue((String) obj);
                }
        }

    }
}
