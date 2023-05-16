package com.example.tema2.Model;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.util.List;
import java.util.stream.Collectors;

public class CSVExporter implements Exporter{
    @Override
    public void export(List list) {

        list.forEach(e -> System.out.println(e.toString()));
        String[] header = {"Id", "List of dishes", "Status", "Cost", "Time", "Date"};
        List<String[]> csvData = (List<String[]>) list.stream()
                .map(e -> e.toString().split(";"))
                .collect(Collectors.toList());
        csvData.add(0, header);

        try (CSVWriter writer = new CSVWriter(new FileWriter("d:\\TEMA2\\raport.csv"))) {
            writer.writeAll(csvData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
