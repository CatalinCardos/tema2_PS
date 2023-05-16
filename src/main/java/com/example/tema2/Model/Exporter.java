package com.example.tema2.Model;

import java.io.IOException;
import java.util.List;

public interface Exporter {
    void export(List list) throws IOException;
}
