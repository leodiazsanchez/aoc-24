package Day4;

import Utils.FileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class part1 {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader<String> fr = new FileReader<>("src/Day4/input.txt", "", String.class);
        ArrayList<ArrayList<String>> rows = fr.getRows();

        String target = "XMAS";
        System.out.println(countOccurrences(rows, target));
    }

    //Returns the char at (r,c) and "." for out-of-bounds
    public static String getCharSafe(ArrayList<ArrayList<String>> rows, int r, int c) {
        int rowCount = rows.size();
        if (r >= 0 && r < rowCount && c >= 0 && c < rows.get(r).size()) {
            return rows.get(r).get(c);
        }
        return ".";
    }
    public static int countOccurrences(ArrayList<ArrayList<String>> rows, String target) {
        int rowCount = rows.size();
        int occurrences = 0;

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < rows.get(r).size(); c++) {
                for (int i = -1; i <= 1; i++) {
                    for (int j = -1; j <= 1; j++) {
                        if (i == 0 && j == 0) continue; // Skip stationary dir

                        StringBuilder word = new StringBuilder();
                        for (int k = 0; k < target.length(); k++) {
                            word.append(getCharSafe(rows, r + k * i, c + k * j));
                        }

                        if (word.toString().equals(target)) {
                            occurrences++;
                        }
                    }
                }
            }
        }
        return occurrences;
    }
}
