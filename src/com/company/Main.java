package com.company;
import com.company.converter.ConvertLines;
import com.company.converter.Converter;
import com.company.converter.ConvertPoints;
import com.company.creator.LinesCreator;
import com.company.entities.Line;
import com.company.entities.Point;
import com.company.extensions.BinExtension;
import com.company.extensions.CSVExtension;
import com.company.extensions.ExtensionConverter;
import com.company.reader.*;
import com.company.binaryManager.*;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {

        // Na początku zapisuje nazwy plików z których będę wczytywał dane,
        // korzystam z metody nadającej podanym wartościom odpowiednie rozszerzenia.
        // Pliki są zapisane w projekcie, więc korzystam z adresów względnych.
        String linesFileName = getFileName("lines", new CSVExtension());
        String pointsFileName = getFileName("points", new CSVExtension());

        // Na tym kroku przypisuje dwie mapy zawierające różne wartości w zależności od plików z jakich są wczytywane dane
        Map<Integer, List<Point>> pointsMap = getConvertedMap(new ConvertPoints(FileReader.readFile(pointsFileName)));
        Map<Integer, Boolean> linesMap = getConvertedMap(new ConvertLines(FileReader.readFile(linesFileName)));

        // Ten krok słuzy do stworzenia listy zawierających obiekty klasy 'Line'. Do jej stworzenia używam map z poprzedniego kroku.
        List<Line> linesList = LinesCreator.createList(pointsMap, linesMap);

        // Określenie ścieżki i rozszerzenia pliku do którego będą zapisywane i odczytywane dane binarne.
        String saveBinaryName = getFileName("binary", new BinExtension());

        // Tworzę obiekt klasy 'BinaryFile' do którego przekazuje parametry niezbędne do zapisania i odczytania danych z plików binarnych
        // Parametry: ścieżka do pliku, lista obiektów klasy 'Line' do zapisania, oraz wartość typu 'boolean' filtrująca dane
        BinaryFile binaryFile = new BinaryFile(saveBinaryName, linesList, true);

        // Ostatni krok. W zależności od podanej implementacji interfejsu 'Activity', poniższa metoda wczytuje, albo zapisuje obiekty do plików
        saveOrLoad(new SaveBinaryFile(binaryFile));
        saveOrLoad(new LoadBinaryFile(binaryFile));
    }

    // Statyczna metoda pomocnicza, służąca do dodania do nazwy pliku odpowiedniego rozszerzenia.
    // Przyjmuje dwie wartości: 'string', oraz interfejs 'ExtensionConverter'.
    // Zwraca wartość 'string'.
    private static String getFileName(String fileName, ExtensionConverter extensionConverter) {
        return extensionConverter.convert(fileName);
    }

    // Statyczna metoda pomocnicza.
    // Przyjmuje parametr typu 'converter'.
    // Zwraca Mapę wartości zależną od podanego typu 'converter'.
    private static Map getConvertedMap(Converter converter) {
        converter.convert();
        return converter.getMap();
    }

    //Statyczna metoda pomocnicza, po przekazaniu odpowiedniego argumentu zapisuje, albo wczytuje dane z pliku.
    private static void saveOrLoad(Activity activity) {
        activity.execute();
    }
}
