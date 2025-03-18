package com.dipfile.spreadsheet.uility;

import com.dipfile.spreadsheet.services.Dots;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelReader {
    private ExcelReader(){}

    public static HashMap<String,String> readBooksFromExcelFile() {
        HashMap<String, String> hashBooks = new LinkedHashMap<>();
        Workbook workbook = null;
        try(FileInputStream inputStream =
                    new FileInputStream(Paths.get(FilePath.HOME + FilePath.UPLOAD_DIR + FilePath.tempDotSummaryName).toFile())){
            workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = firstSheet.iterator();

            while (iterator.hasNext()){
                Row nextRow = iterator.next();
                Dots book = workSheet(nextRow);
                if(book.getFieldName() != null && book.getFieldId() != null){
                    hashBooks.put(book.getFieldId(), book.getFieldName());
                }
            }
            workbook.close();

        }catch (IOException e){
            e.printStackTrace();
        }
        return hashBooks;
    }

    private static Object getCellValue(Cell cell){
        switch (cell.getCellType()){
            case STRING:
                return cell.getStringCellValue();
        }
        return null;
    }

    private static boolean findSpecialChar(String col1){
        Pattern special = Pattern.compile("[!@#$%&*().+=|<>?{}\\[\\]~-]");
        Matcher m = special.matcher(col1);
        return m.find();
    }

    private static Dots workSheet(Row nextRow){
        Iterator<Cell> cellIterator = nextRow.cellIterator();
        Dots aBook = new Dots();

        while (cellIterator.hasNext()){
            Cell nextCell = cellIterator.next();
            int columnIndex = nextCell.getColumnIndex();
            String columnHeader =
                    nextCell.getSheet().getRow(0).getCell(columnIndex).getRichStringCellValue().toString();

            switch (columnHeader.toUpperCase()){
                case "FIELD ID":
                    String col1 = ((String) getCellValue(nextCell)).toUpperCase();
                    if(col1.equalsIgnoreCase("FIELD ID")){
                        break;
                    }

                    if(findSpecialChar(col1)) {
                        col1 = col1.replace(".","_");
                    }
                    aBook.setFieldId(col1.trim());
                    break;
            }
        }
        return aBook;

    }
}
