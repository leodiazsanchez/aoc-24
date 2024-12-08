package Day7;

import Utils.FileReader;
import Utils.Tuple;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class part1 {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader<String> fr = new FileReader<>("src/Day7/input.txt", "\n", String.class);
        ArrayList<ArrayList<String>> rows = fr.getRows();

        long counter = 0;
        for (ArrayList<String> r : rows) {
            for (String s : r) {
                Tuple<Long, ArrayList<Long>> eq = prepEq(s);
                counter += trySolveEq(eq);
            }
        }

        System.out.println(counter);
    }

    public static long trySolveEq(Tuple<Long, ArrayList<Long>> eq) {
        long target = eq.x;
        ArrayList<Long> nums = eq.y;

        char[] operators = {'+', '*'};
        int numOperators = nums.size() - 1;
        long totalCombinations = (long) Math.pow(operators.length, numOperators);

        for (long i = 0; i < totalCombinations; i++) {
            char[] currentOps = new char[numOperators];
            long temp = i;

            // Base 10 to base b algo (operators.length) to generate all possible combinations
            for (int j = 0; j < numOperators; j++) {
                // Picks an operator
                currentOps[j] = operators[(int) (temp % operators.length)];
                // Shifts to the next slot in base b (operators.length)
                temp = temp / operators.length;
            }

            // Evaluate expr ltr
            long result = nums.get(0);
            for (int k = 0; k < currentOps.length; k++) {
                if (currentOps[k] == '+') {
                    result += nums.get(k + 1);
                } else if (currentOps[k] == '*') {
                    result *= nums.get(k + 1);
                }
            }

            if (result == target) {
                return result;
            }
        }

        return 0;
    }

    public static Tuple<Long, ArrayList<Long>> prepEq(String s) {
        String[] temp = s.split(":");
        long target = Long.parseLong(temp[0]);

        ArrayList<Long> nums = new ArrayList<>();
        String[] tempNums = temp[1].substring(1).split(" ");

        for (String str : tempNums) {
            nums.add(Long.parseLong(str));
        }

        return new Tuple<>(target, nums);
    }
}
