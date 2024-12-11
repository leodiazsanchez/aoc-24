package Day11;

import Utils.FileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class part1 {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader<String> fr = new FileReader<>("src/Day11/input.txt", "", String.class);
        String input = fr.getString();
        ArrayList<Integer> arr = makeList(input);
        blink(arr, 1);
        System.out.println(arr);
    }

    public static void blink(ArrayList<Integer> stones, int iterations){
        for (int i = 0; i < iterations; i++) {
            for (int j = 0; j < stones.size(); j++) {
                int stone = stones.get(j);
                int nrOfDigits = String.valueOf(stone).length();
                String stoneAsString = String.valueOf(stone);
                if(stone == 0){
                    stones.set(j, 1);
                } else if(nrOfDigits % 2 == 0){
                    int left = Integer.parseInt(stoneAsString.substring(0, (stoneAsString.length()/2)));
                    int right =  Integer.parseInt(stoneAsString.substring(stoneAsString.length()/2));
                    stones.set(j, left);
                    stones.set(j+1, right);
                } else {
                    stones.set(j, stone*2024);
                }
            }
        }
    }

    public static ArrayList<Integer> makeList(String str){
        String[] a = str.split(" ");
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            arr.add(Integer.valueOf(a[i]));
        }
        return arr;
    }
}
