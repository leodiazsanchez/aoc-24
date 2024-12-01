package Day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Arrays;

public class part1 {
    public static void main(String[] args) {
        int i = 0;
        int[] left = new int[1000];
        int[] right = new int[1000];
        try {
            File input = new File("src/Day1/input.txt");
            Scanner reader = new Scanner(input);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] arr = data.split("\\s+");
                left[i] = Integer.parseInt(arr[0]);
                right[i] = Integer.parseInt(arr[1]);
                i++;
            }

            Arrays.sort(left);
            Arrays.sort(right);
            int res = 0;
            for (int j = 0; j < left.length; j++){
                res += Math.abs(left[j] - right[j]);
            }
            System.out.println(res);
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
