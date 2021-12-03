package com.company.converter;
import java.util.Map;

public interface Converter {
    void convert();
    Map getMap();

    // Defaultowa metoda pomocnicza wykorzystywana w głównej metodzie 'convert' w klasach implementujących interfejs "Converter",
    // Parsuje podany parametr 'String' na wartosc 'int'.
    // W przypadku błędnej wartości wyrzuca wyjątek.
    // Zwraca wartość typu 'int'
    default int setIndex(String indexNumber) {
        try {
            return Integer.parseInt(indexNumber);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("'" + indexNumber + "'" + " is not an integer");
        }
    }
}
