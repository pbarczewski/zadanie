package com.company.reader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Interfejs zawierający jedną metodę statyczną.
// Metoda służy do wczytania danych z pliku.
// Zwraca Listę list zawierających wartości typu 'string'.
public interface FileReader {
    static List<List<String>> readFile(String fileName) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(fileName))) {
            String singleLine;
            while ((singleLine = br.readLine()) != null) {
                String[] rows = singleLine.split(";");
                records.add(Arrays.asList(rows));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(records.size() + " elements have been read from " + fileName);
        return records;
    }
}
