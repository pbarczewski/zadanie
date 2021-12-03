package com.company.creator;
import com.company.entities.Line;
import com.company.entities.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LinesCreator {
    private final static List<Line> lineList = new ArrayList<>();

    // Statyczna metoda tworząca listę obiektów klasy 'Line'.
    // Przyjmuje dwa argumenty: Map<Integer, List<Point>, oraz Map<Integer, Boolean>.
    // Rzuca wyjątek jeżeli któryś z argumentów zawiera pustą mapę.
    // Metoda zwraca listę obiektów typu 'Line'.
    public static List<Line> createList(Map<Integer, List<Point>> pointsMap, Map<Integer, Boolean> linesMap) throws Exception {
        if(pointsMap.size() == 0 || linesMap.size() == 0) {
            throw new Exception("One or both of maps are empty");
        }
        for(Map.Entry<Integer,List<Point>> singlePoint : pointsMap.entrySet()) {
            if(linesMap.containsKey(singlePoint.getKey())) {
                Line line = new Line(singlePoint.getValue().toArray(new Point[0]), linesMap.get(singlePoint.getKey()));
                lineList.add(line);
            }
        }
        return lineList;
    }
}
