package com.company.converter;
import com.company.entities.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertPoints implements Converter {
    public final Map<Integer, List<Point>> convertedData = new HashMap<>();
    private final List<List<String>> data;

    public ConvertPoints(List<List<String>> data) throws Exception {
        if (data == null || data.size() == 0) {
            throw new Exception("The list is empty");
        }
        this.data = data;
    }

    // Główna metoda klasy, implementowana z interfejsu 'Converter'.
    // Wewnątrz metody użyte zostały prywatne metody pomocnicze: 'pointCreator()', 'fillPointsList()', oraz defaultowa metoda 'setIndex()' z interfejsu 'Converter'.
    // Nie przyjmuje żadnych parametrów.
    // Metoda Niczego nie zwraca.
    @Override
    public void convert() {
        Point point;
        int index;
        for(List singleList : this.data) {
            String pointX = (String) singleList.get(0);
            String pointY = (String) singleList.get(1);
            String indexNumber = (String) singleList.get(singleList.size() -1);
            index = setIndex(indexNumber);
            point = pointCreator(pointX, pointY);
            createConvertedData(index, point);
        }
    }

    // Prywatna metoda pomocnicza wykorzystywana w głównej metodzie 'convert'.
    // Przyjmuje dwa argumenty typu 'string', konwertuje je na typ 'float' i tworzy obiekt klasy 'Point' na ich podstawie.
    // Metoda rzuca wyjątek, jężeli podane parametry są nieprawidłowe.
    // Zwraca obiekt klasy 'Point'.
    private Point pointCreator(String pointX, String pointY) {
        try {
            float x = Float.parseFloat(pointX);
            float y = Float.parseFloat(pointY);
            return new Point(x, y);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("One or two of the arguments are not a float value");
        }
    }

    // Prywatna metoda pomocnicza wykorzystywana w głównej metodzie 'convert'.
    // Na podstawie podanych parametrów wypełnia mapę o wartościach : {"index" : "List<Point>"}.
    // Metoda przyjmuje parametry 'int' i 'Point'.
    // Metoda niczego nie zwraca
    private void createConvertedData(int index, Point point) {
        if(!this.convertedData.containsKey(index)) {
            this.convertedData.put(index, new ArrayList<>());
        }
        this.convertedData.get(index).add(point);
    }

    // Implementowana metoda zwracająca mapę o wartościach : {"int" : "List<Point"}
    @Override
    public Map getMap() {
        return convertedData;
    }
}
