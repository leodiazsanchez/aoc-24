package Day1;

import Utils.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class part1 {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader<Integer> fr = new FileReader<>("src/Day1/input.txt", "\\s+", Integer.class);
        ArrayList<ArrayList<Integer>> cols = fr.getCols(2);
        ArrayList<Integer> left = cols.get(0);
        ArrayList<Integer> right = cols.get(1);

        Collections.sort(left);
        Collections.sort(right);

        int res = 0;
        for (int i = 0; i < left.size(); i++){
            res += Math.abs(left.get(i) - right.get(i));
        }
        System.out.println(res);
    }
}
