package com.company.extensions;

public class CSVExtension implements ExtensionConverter {
    // Metoda dodaje końcówkę '.csv' do podanej nazwy pliku.
    // Zwraca wartośc typu 'string'.
    @Override
    public String convert(String fileName) {
        return fileName.split("\\.")[0] + ".csv";
    }
}
