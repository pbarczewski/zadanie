package com.company.extensions;

public class BinExtension implements ExtensionConverter {
    // Metoda dodaje końcówkę '.bin' do podanej nazwy pliku.
    // Zwraca wartośc typu 'string'.
    @Override
    public String convert(String fileName) {
        return fileName.split("\\.")[0] + ".bin";
    }
}
