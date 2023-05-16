package com.example.tema2.Utils;

import com.example.tema2.Model.CSVExporter;
import com.example.tema2.Model.ExcelExporter;
import com.example.tema2.Model.Exporter;

public class ExporterFactory {
    public static Exporter getExporter(String type){
        if(type.equals("csv")){
            return new CSVExporter();
        }
        else if(type.equals("excel")){
            return new ExcelExporter();
        }
        else{
            return null;
        }
    }
}
