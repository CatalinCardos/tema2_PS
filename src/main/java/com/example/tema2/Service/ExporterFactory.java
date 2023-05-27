package com.example.tema2.Service;

import com.example.tema2.Service.CSVExporter;
import com.example.tema2.Service.ExcelExporter;
import com.example.tema2.Service.Exporter;

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
