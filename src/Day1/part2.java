package Day1;

import Utils.FileReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.HashMap;

public class part2 {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader<Integer> fr = new FileReader<>("src/Day1/input.txt", "\\s+", Integer.class);
        ArrayList<ArrayList<Integer>> cols = fr.getCols(2);
        ArrayList<Integer> left = cols.get(0);
        ArrayList<Integer> right = cols.get(1);

        //This could be done on read to reduce time complexity...
        //Did it this way to make the code cleaner
        HashMap<Integer, Integer> lookup = new HashMap<Integer, Integer>();
        for (Integer i : right) {
            if(lookup.containsKey(i)){
                int oldKey = lookup.get(i);
                lookup.replace(i, ++oldKey);
            } else {
                lookup.put(i,1);
            }
        }

        int res = 0;
        for (int i = 0; i < left.size(); i++){
            if(lookup.get(left.get(i)) != null){
                res += left.get(i) * lookup.get(left.get(i));
            }
        }

        System.out.println(res);
    }
}
