package Day2;

import Utils.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class part1 {
    public static void main(String[] args) throws FileNotFoundException {
       int res = 0;
        FileReader<Integer> fr = new FileReader<>("src/Day2/input.txt", " ", Integer.class);
        ArrayList<ArrayList<Integer>> rows = fr.getRows();
        for (ArrayList<Integer> row : rows){
            if (safe(row)){
                res++;
            }
        }
        System.out.println(res);
    }
    public static boolean safe(ArrayList<Integer> row) {
        boolean asc = row.get(0) < row.get(1);

        for (int i = 1; i < row.size(); i++) {
            int num = row.get(i);
            int prev = row.get(i - 1);
            int diff = Math.abs(prev - num);

            if ((asc && prev > num) || (!asc && prev < num) || diff < 1 || diff > 3) {
                return false;
            }
        }
        return true;
    }
}