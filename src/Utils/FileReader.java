package Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FileReader {
    Scanner reader;
    String delimiter;

    public FileReader (String path, String delimiter) throws FileNotFoundException {
        reader = new Scanner(new File(path));
        this.delimiter = delimiter;
    }

    public ArrayList<ArrayList<String>> getRows(){
        ArrayList<ArrayList<String>> arr = new ArrayList<>();
        while (reader.hasNextLine()){
            String data = reader.nextLine();
            String[] split = data.split(delimiter);
            ArrayList<String> strings = new ArrayList<>(Arrays.asList(split));
            arr.add(strings);
        }
        return arr;
    }
}
