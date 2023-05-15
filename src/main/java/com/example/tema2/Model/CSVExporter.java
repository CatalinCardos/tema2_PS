package com.example.tema2.Model;

import java.util.List;

public class CSVExporter implements Exporter{
    @Override
    public void export(List list) {

        list.forEach(System.out::println);

    }
}
