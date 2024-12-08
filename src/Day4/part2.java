package Day4;

import Utils.FileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class part2 {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader<String> fr = new FileReader<>("src/Day4/input.txt", "", String.class);
        ArrayList<ArrayList<String>> rows = fr.getRows();

        int occurrences = countOccurrences(rows);
        System.out.println(occurrences);
    }

    //Returns the char at (r,c) and "." for out-of-bounds
    public static String getCharSafe(ArrayList<ArrayList<String>> rows, int r, int c) {
        int rowsCount = rows.size();
        if (r >= 0 && r < rowsCount && c >= 0 && c < rows.get(r).size()) {
            return rows.get(r).get(c);
        }
        return ".";
    }
    public static int countOccurrences(ArrayList<ArrayList<String>> rows) {
        int rowCount = rows.size();
        int occurrences = 0;

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < rows.get(r).size(); c++) {
                if ((getCharSafe(rows, r, c)).equals("A")) {
                    // Diagonal "/"
                    String diag1 = getDiagonal(rows, r - 1, c - 1, r + 1, c + 1);
                    // Diagonal "\"
                    String diag2 = getDiagonal(rows, r - 1, c + 1, r + 1, c - 1);

                    if (isValidDiagonal(diag1) && isValidDiagonal(diag2)) {
                        occurrences++;
                    }
                }
            }
        }
        return occurrences;
    }

    public static String getDiagonal(ArrayList<ArrayList<String>> rows, int r1, int c1, int r2, int c2) {
        return getCharSafe(rows, r1, c1) + getCharSafe(rows, r2, c2);
    }

    public static boolean isValidDiagonal(String diag) {
        return diag.equals("SM") || diag.equals("MS");
    }
}