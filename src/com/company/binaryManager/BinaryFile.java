package com.company.binaryManager;
import com.company.entities.Line;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class BinaryFile {
    private final String fileName;
    private final List<Line> lineList;
    private final boolean toSave;

    public BinaryFile(String fileName, List<Line> lineList, boolean toSave) {
        if(fileName.equals("") || lineList.size() == 0) {
            throw new IllegalArgumentException("Parameters are wrong");
        }
        this.fileName = fileName;
        this.lineList = lineList;
        this.toSave = toSave;
    }

    // Metoda służąca do zapisania w postaci binarnej listy obiektów do wskazanej ścieżki.
    // Nie przyjmuje żadnych parametrów.
    // Wykorzystuje metodę pomocniczą 'filterFiles()'.
    // Metoda niczego nie zwraca.
    public void saveFile() {
        List<Line> objectsToSave = filterFiles(this.toSave);
        try
                (
                FileOutputStream fileOutputStream = new FileOutputStream(fileName);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
                )
        {
            objectOutputStream.writeObject(objectsToSave);
            System.out.println(objectsToSave.size() + " elements has been saved to " + fileName);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Prywatna metoda pomocnicza służąca do stworzenia listy obiektów jakie mają być zapisane do pliku.
    // Przyjmuje parametr 'boolean' służący do filtrowania obiektów.
    // Zwraca listę obiektów dla których wartość 'someFlag' klasy 'Line' jest zgodna z parametrem 'isTrue'.
    private List<Line> filterFiles(boolean isTrue) {
        return lineList.stream()
        .filter(line -> line.someFlag == isTrue)
        .collect(Collectors.toList());
    }

    // Metoda służąca do odczytu danych binarnych z podanej scieżki.
    // Nie przyjmuje parametrów.
    // Zwraca listę obiektów klasy 'Line'.
    public List<Line> loadFile() {
        List<Line> deserializedLines = null;
        try
                (
                FileInputStream fileOInputStream = new FileInputStream(fileName);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileOInputStream)
                )
        {
            deserializedLines = (List<Line>) objectInputStream.readObject();
            System.out.println(deserializedLines.size() + " elements have been read from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return deserializedLines;
    }
}
