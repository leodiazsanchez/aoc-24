package Day5;

import Utils.FileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public class part1 {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader<Integer> fr = new FileReader<>("src/Day5/input.txt", "\\|", Integer.class);
        ArrayList<ArrayList<Integer>> col = fr.getCols(2);
        ArrayList<Integer> left = col.get(0);
        ArrayList<Integer> right = col.get(1);

        HashMap<Integer, ArrayList<Integer>> lookup = new HashMap<Integer, ArrayList<Integer>>();

        for (int i=0; i<left.size(); i++) {
            int l = left.get(i);
            int r = right.get(i);
            if(lookup.containsKey(r)){
                lookup.get(r).add(l);
            } else {
                lookup.put(r,new ArrayList<>());
                lookup.get(r).add(l);
            }
        }

        System.out.println("Rules: " + lookup);

        FileReader<Integer> fr2 = new FileReader<>("src/Day5/input2.txt", ",", Integer.class);
        ArrayList<ArrayList<Integer>> rows = fr2.getRows();

        ArrayList<ArrayList<Integer>> successful = new ArrayList<>();
        for (ArrayList<Integer> row : rows){
            if(isValidRow(row,lookup)){
                successful.add(row);
            }
        }

        int sum = 0;
        for(ArrayList<Integer> s: successful){
            sum += s.get(s.size()/2);
        }

        System.out.println(sum);
    }

    public static boolean isValidRow(ArrayList<Integer> row, HashMap<Integer, ArrayList<Integer>> lookup ){
        for (Integer n : row){
            ArrayList<Integer> rule = lookup.get(n);
            System.out.println("N: " + n);
            System.out.println("Rule for n: " + rule);
            if(rule != null){
                for (int r : rule) {
                    if (row.contains(r) && row.indexOf(r) > row.indexOf(n)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}