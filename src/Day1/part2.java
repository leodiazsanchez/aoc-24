package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;

public class part2 {
    public static void main(String[] args) {
        int i = 0;
        int[] left = new int[1000];
        HashMap<Integer, Integer> right = new HashMap<Integer, Integer>();
        try {
            File input = new File("src/Day1/input.txt");
            Scanner reader = new Scanner(input);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] arr = data.split("\\s+");
                left[i] = Integer.parseInt(arr[0]);

                if(right.containsKey(Integer.parseInt(arr[1]))){
                    int oldKey = right.get(Integer.parseInt(arr[1]));
                    right.replace(Integer.parseInt(arr[1]), ++oldKey);
                } else {
                    right.put(Integer.parseInt(arr[1]),1);
                }

                i++;
            }
            reader.close();

            int res = 0;
            for (int j = 0; j < left.length; j++){
                if(right.get(left[j]) != null){
                    res += left[j] * right.get(left[j]);
                }
            }

            System.out.println(res);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
