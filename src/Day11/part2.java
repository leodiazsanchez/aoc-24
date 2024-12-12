package Day11;

import Utils.FileReader;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class part2 {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader<String> fr = new FileReader<>("src/Day11/input.txt", "", String.class);
        String input = fr.getString();
        HashMap<BigInteger, BigInteger> arr = makeList(input);
        blink(arr, 75);
        System.out.println(arr);
        System.out.println(calcSize(arr));
    }

    public static void blink(HashMap<BigInteger, BigInteger> stones, int iterations) {
        for (int i = 0; i < iterations; i++) {
            Iterator<Map.Entry<BigInteger, BigInteger>> iterator = stones.entrySet().iterator();
            HashMap<BigInteger, BigInteger> temp = new HashMap<>();

            while (iterator.hasNext()) {
                Map.Entry<BigInteger, BigInteger> entry = iterator.next();
                BigInteger stone = entry.getKey();
                BigInteger count = entry.getValue();
                String stoneAsString = stone.toString();
                int nrOfDigits = stoneAsString.length();

                iterator.remove();

                if (stone.equals(BigInteger.ZERO)) {
                    addToMap(temp, BigInteger.ONE, count);
                } else if (nrOfDigits % 2 == 0) {
                    BigInteger left = new BigInteger(stoneAsString.substring(0, nrOfDigits / 2));
                    BigInteger right = new BigInteger(stoneAsString.substring(nrOfDigits / 2));
                    addToMap(temp, left, count);
                    addToMap(temp, right, count);
                } else {
                    BigInteger transformed = stone.multiply(BigInteger.valueOf(2024));
                    addToMap(temp, transformed, count);
                }
            }

            temp.forEach((key, value) -> stones.merge(key, value, BigInteger::add));
        }
    }

    private static void addToMap(HashMap<BigInteger, BigInteger> map, BigInteger key, BigInteger value) {
        map.merge(key, value, BigInteger::add);
    }

    public static HashMap<BigInteger, BigInteger> makeList(String str) {
        HashMap<BigInteger, BigInteger> stones = new HashMap<>();
        for (String s : str.split(" ")) {
            addToMap(stones, new BigInteger(s), BigInteger.ONE);
        }
        return stones;
    }

    static BigInteger calcSize(HashMap<BigInteger, BigInteger> map) {
        return map.values().stream()
                .reduce(BigInteger.ZERO, BigInteger::add);
    }
}
