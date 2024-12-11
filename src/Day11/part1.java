package Day11;

import Utils.FileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class part1 {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader<String> fr = new FileReader<>("src/Day11/input.txt", "", String.class);
        String input = fr.getString();
        ArrayList<Integer> arr = makeList(input);
        ArrayList<Integer> arr2 = blink(arr, 25);
        System.out.println(arr2.size());
    }

    public static ArrayList<Integer> blink(ArrayList<Integer> stones, int iterations){
        for (int i = 0; i < iterations; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            for (int stone : stones) {
                int nrOfDigits = String.valueOf(stone).length();
                String stoneAsString = String.valueOf(stone);
                if (stone == 0) {
                    temp.add(1);
                } else if (nrOfDigits % 2 == 0) {
                    int left = Integer.parseInt(stoneAsString.substring(0, (stoneAsString.length() / 2)));
                    int right = Integer.parseInt(stoneAsString.substring(stoneAsString.length() / 2));
                    temp.add(left);
                    temp.add(right);
                } else {
                    temp.add(stone * 2024);
                }
                stones = temp;
            }

            if(i == iterations-1){
                return temp;
            }
        }

        return new ArrayList<>();
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
