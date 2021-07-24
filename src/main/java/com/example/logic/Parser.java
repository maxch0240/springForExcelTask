package com.example.logic;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Parser {

    private final List<Info> listInfo = new LinkedList<>();


    public void parse(String name) throws IOException {
        InputStream in = new FileInputStream(name);
        HSSFWorkbook wb = new HSSFWorkbook(in);

        Sheet sheet = wb.getSheetAt(0);

        Iterator<Row> it = sheet.iterator();

        //skip first 9 rows
        {
            it.next();
            it.next();
            it.next();
            it.next();
            it.next();
            it.next();
            it.next();
            it.next();
            it.next();
        }

        while (it.hasNext()) {
            Row row = it.next();
            Iterator<Cell> cells = row.iterator();

            Cell cell1 = cells.next();
            if (cellCheck(cell1)) continue;

            Cell cell2 = cells.next();
            Cell cell3 = cells.next();
            Cell cell4 = cells.next();
            Cell cell5 = cells.next();
            Cell cell6 = cells.next();
            Cell cell7 = cells.next();
            int bAccount;

            try {
                bAccount = Integer.parseInt(cell1.getStringCellValue());
            } catch (IllegalStateException e) {
                bAccount = (int) cell1.getNumericCellValue();
            }


            listInfo.add(new Info(
                    bAccount,
                    cell2.getNumericCellValue(),
                    cell3.getNumericCellValue(),
                    cell4.getNumericCellValue(),
                    cell5.getNumericCellValue(),
                    cell6.getNumericCellValue(),
                    cell7.getNumericCellValue()
            ));
        }
    }

    private static boolean isNumeric(String str) {
        return str.matches("((-|\\\\+)?[0-9]+(\\\\.[0-9]+)?)+");
    }

    //check first cell,if string cannot be number, or blank row, or formula
    private static boolean cellCheck(Cell cell) {
        int cellType = cell.getCellType();
        if (cellType == Cell.CELL_TYPE_BLANK || cellType == cell.CELL_TYPE_FORMULA) {
            return true;
        } else if (cellType == Cell.CELL_TYPE_STRING) {
            String str = cell.getStringCellValue();
            return !isNumeric(str);
        }
        return false;
    }

    public List<Info> returnInfo() {
        return this.listInfo;
    }
}
