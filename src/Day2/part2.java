package Day2;

import Utils.FileReader;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class part2 {
    public static void main(String[] args) throws FileNotFoundException {
        int counter = 0;
        FileReader fr = new FileReader("src/Day2/input.txt", " ");
        ArrayList<ArrayList<String>> rows = fr.getRows();
        for (ArrayList<String> row : rows){
            for (int  i= 0; i < row.size(); i++) {
                ArrayList<String> subList = new ArrayList<>(row);
                subList.remove(i);
                if(safe(subList)){
                    counter++;
                    break;
                }
            }
        }
        System.out.println(counter);
    }
    public static boolean safe(ArrayList<String> row) {
        boolean asc = Integer.parseInt(row.get(0)) < Integer.parseInt(row.get(1));

        for (int i = 1; i < row.size(); i++) {
            int num = Integer.parseInt(row.get(i));
            int prev = Integer.parseInt(row.get(i - 1));
            int diff = Math.abs(prev - num);

            if ((asc && prev > num) || (!asc && prev < num) || diff < 1 || diff > 3) {
                return false;
            }
        }
        return true;
    }
}