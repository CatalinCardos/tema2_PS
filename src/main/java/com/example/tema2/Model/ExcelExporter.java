package com.example.tema2.Model;

import java.util.List;

public class ExcelExporter implements Exporter{
    @Override
    public void export(List list) {

        try {

            // Create an instance of the class that exports Excel files, having two sheets
            ExcelDocument workbook = new ExcelDocument(2);

            // Set the sheet names
            workbook.easy_getSheetAt(0).setSheetName("First tab");
            workbook.easy_getSheetAt(1).setSheetName("Second tab");

            // Get the table of data for the first worksheet
            ExcelTable xlsFirstTable = ((ExcelWorksheet)workbook.easy_getSheetAt(0)).easy_getExcelTable();

            // Add data in cells for report header
            for (int column=0; column<5; column++)
            {
                xlsFirstTable.easy_getCell(0,column).setValue("Column " + (column + 1));
                xlsFirstTable.easy_getCell(0,column).setDataType(DataType.STRING);
            }

            // Add data in cells for report values
            for (int row=0; row<100; row++)
            {
                for (int column=0; column<5; column++)
                {
                    xlsFirstTable.easy_getCell(row+1,column).setValue("Data " + (row + 1) + ", " + (column + 1));
                    xlsFirstTable.easy_getCell(row+1,column).setDataType(DataType.STRING);
                }
            }

            // Export the XLSX file
            System.out.println("Writing file: C:\\Samples\\Tutorial04 - export data to Excel.xlsx");
            workbook.easy_WriteXLSXFile("C:\\Samples\\Tutorial04 - export data to Excel.xlsx");

            // Confirm export of Excel file
            if (workbook.easy_getError().equals(""))
                System.out.println("File successfully created.");
            else
                System.out.println("Error encountered: " + workbook.easy_getError());

            // Dispose memory
            workbook.Dispose();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
