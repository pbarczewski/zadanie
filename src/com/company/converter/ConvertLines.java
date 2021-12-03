package com.company.converter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertLines implements Converter {
    private final List<List<String>> data;
    private final Map<Integer, Boolean> convertedData = new HashMap<>();

    public ConvertLines(List<List<String>> data) throws Exception {
        if (data == null || data.size() == 0) {
            throw new Exception(data + " is empty");
        }
        this.data = data;
    }

    // Główna metoda klasy, implementowana z interfejsu "Converter".
    // Wewnątrz metody użyte zostały prywatne metody pomocnicze: 'checkBooleanValue', 'fillLinesMap()', oraz defaultowa metoda 'setIndex()'.
    // Metoda nie przyjmuje żadnych parametrów.
    // Metoda niczego nie zwraca.
    @Override
    public void convert() {
        int index;
        boolean isTrue;
        for(List singleList : this.data) {
            String indexNumber = (String) singleList.get(0);
            String booleanValue = (String) singleList.get(1);
            index = setIndex(indexNumber);
            isTrue = checkBooleanValue(booleanValue);
            createConvertedData(index, isTrue);
        }
    }

    // Prywatna metoda pomocnicza wykorzystywana w głównej metodzie 'convert'.
    // Przyjmuje parametr typu 'string' i konwertuje go na typ 'boolean'.
    // W przypadku gdy parametr jest niepoprawny, wyrzuca wyjątek 'IllegalArgumentException'.
    // Zwraca wartość 'boolean'.
    private boolean checkBooleanValue(String booleanValue) {
        if ("true".equalsIgnoreCase(booleanValue) || "false".equalsIgnoreCase(booleanValue)) {
            return Boolean.parseBoolean(booleanValue);
        } else {
            throw new IllegalArgumentException("Wrong booleanValue");
        }
    }

    // Prywatna metoda pomocnicza wykorzystywana w głównej metodzie 'convert'.
    // Metoda przyjmuje dwa parametry 'int' i 'boolean'.
    // Na podstawie podanych parametrów wypełnia mapę o wartościach : {"int" : "boolean"}.
    // Nie zwraca żadnej wartości.
    private void createConvertedData(int index, boolean booleanValue) {
        if(!convertedData.containsKey(index)) {
            convertedData.put(index, booleanValue);
        }
    }

    // Implementowana metoda zwracająca mapę o wartościach : {"int" : "boolean"}.
    @Override
    public Map getMap() {
        return convertedData;
    }
}
