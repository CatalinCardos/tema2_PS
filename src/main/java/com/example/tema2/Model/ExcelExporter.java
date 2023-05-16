package com.example.tema2.Model;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter implements Exporter{
    @Override
    public void export(List list) throws IOException {

        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("Orders");
        Row header = sheet.createRow(0);

        CellStyle headerStyle = workbook.createCellStyle();

        XSSFFont font = ((XSSFWorkbook) workbook).createFont();
        font.setFontName("Arial");
        headerStyle.setFont(font);
        Cell headerCell;
        String[] headerData = {"Id", "List of dishes", "Status", "Cost", "Time", "Date"};
        int i = 0;
        for (String data : headerData) {
            headerCell = header.createCell(i);
            headerCell.setCellValue(headerData[i]);
            headerCell.setCellStyle(headerStyle);
            i++;
        }
        CellStyle style = workbook.createCellStyle();

        i = 1;
        for (Object order : list) {
            Row row = sheet.createRow(i);
            Cell cell = row.createCell(0);
            cell.setCellValue(((OrderFromMenu) order).getId());
            cell.setCellStyle(style);

            cell = row.createCell(1);
            cell.setCellValue(((OrderFromMenu) order).getDishList().toString());
            cell.setCellStyle(style);

            cell = row.createCell(2);
            cell.setCellValue(((OrderFromMenu) order).getStatus().toString());
            cell.setCellStyle(style);

            cell = row.createCell(3);
            cell.setCellValue(((OrderFromMenu) order).getTotalCost());
            cell.setCellStyle(style);

            cell = row.createCell(4);
            cell.setCellValue(((OrderFromMenu) order).getTime().toString());
            cell.setCellStyle(style);

            cell = row.createCell(5);
            cell.setCellValue(((OrderFromMenu) order).getDate().toString());
            cell.setCellStyle(style);
            i++;
        }

        String fileLocation = "d:\\TEMA2\\raport.xlsx";

        FileOutputStream outputStream = new FileOutputStream(fileLocation);
        workbook.write(outputStream);
        workbook.close();

    }
}
