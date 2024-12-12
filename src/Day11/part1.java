package Day11;

import Utils.FileReader;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.LinkedList;

public class part1 {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader<String> fr = new FileReader<>("src/Day11/input.txt", "", String.class);
        String input = fr.getString();
        LinkedList<BigInteger> arr = makeList(input);
        blink(arr, 25);
        System.out.println(arr.size());
    }

    public static void blink(LinkedList<BigInteger> stones, int iterations) {
        for (int i = 0; i < iterations; i++) {
            int size = stones.size();
            for (int j = 0; j < size; j++) {
                BigInteger stone = stones.poll();
                int nrOfDigits = stone.toString().length();
                String stoneAsString = stone.toString();
                if (stone.equals(BigInteger.ZERO)) {
                    stones.add(BigInteger.ONE);
                } else if (nrOfDigits % 2 == 0) {
                    BigInteger left = new BigInteger(stoneAsString.substring(0, stoneAsString.length() / 2));
                    BigInteger right = new BigInteger(stoneAsString.substring(stoneAsString.length() / 2));
                    stones.add(left);
                    stones.add(right);
                } else {
                    stones.add(stone.multiply(BigInteger.valueOf(2024)));
                }
            }
        }
    }

    public static LinkedList<BigInteger> makeList(String str) {
        String[] a = str.split(" ");
        LinkedList<BigInteger> arr = new LinkedList<>();
        for (String s : a) {
            arr.add(new BigInteger(s));
        }
        return arr;
    }
}
