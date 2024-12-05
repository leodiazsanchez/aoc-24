package Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FileReader<T> {
    Scanner reader;
    String delimiter;
    private final Class<T> type;

    public FileReader (String path, String delimiter, Class<T> type) throws FileNotFoundException {
        reader = new Scanner(new File(path));
        this.delimiter = delimiter;
        this.type = type;
    }

    public String getString(){
        String str = "";
        while (reader.hasNextLine()){
            str += reader.nextLine();
        }
        return str;
    }

    public ArrayList<ArrayList<T>> getRows(){
        ArrayList<ArrayList<T>> arr = new ArrayList<>();
        while (reader.hasNextLine()){
            String data = reader.nextLine();
            String[] split = data.split(delimiter);
            ArrayList<T> rows = convertArray(split, type);
            arr.add(rows);
        }
        return arr;
    }

    public ArrayList<ArrayList<T>> getCols(int cols){
        ArrayList<ArrayList<T>> arr = new ArrayList<>();
        for (int i = 0; i < cols; i++) {
            arr.add(new ArrayList<T>());
        }
        while (reader.hasNextLine()){
            String data = reader.nextLine();
            String[] split = data.split(delimiter);
            for (int i = 0; i < cols; i++) {
                try {
                    T instance = type.getConstructor(String.class).newInstance(split[i]);
                    arr.get(i).add(instance);
                } catch (Exception e) {
                    throw new RuntimeException("Conversion failed for: " + split[i], e);
                }
            }
        }
        return arr;
    }

    public static <T> ArrayList<T> convertArray(String[] split, Class<T> type) {
        ArrayList<T> arr = new ArrayList<>();
        for (String str : split) {
            try {
                T instance = type.getConstructor(String.class).newInstance(str);
                arr.add(instance);
            } catch (Exception e) {
                throw new RuntimeException("Conversion failed for: " + str, e);
            }
        }
        return arr;
    }
}
