package Day13;

import Utils.Tuple;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class part1 {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<ArrayList<Integer>> arr = parseInput();
        System.out.println(solveEqs(arr));
    }

    static ArrayList<ArrayList<Integer>> parseInput() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("src/Day13/input.txt"));
        ArrayList<ArrayList<Integer>> input = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (!line.trim().isEmpty()) {
                input.add(extractNumbers(line));
            }
        }
        return input;
    }

    static ArrayList<Integer> extractNumbers(String input) {
        ArrayList<Integer> numbers = new ArrayList<>();

        Pattern pattern = Pattern.compile("[+-]?\\d+");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
        }

        return numbers;
    }

    static int solveEqs(ArrayList<ArrayList<Integer>> arr) {
        int cost = 0;
        System.out.println(arr);
        for (int i = 0; i < arr.size(); i += 3) {
            if (i + 2 < arr.size()) {
                int aX = arr.get(i).get(0);
                int aY = arr.get(i).get(1);

                int bX = arr.get(i + 1).get(0);
                int bY = arr.get(i + 1).get(1);

                int cX = arr.get(i + 2).get(0);
                int cY = arr.get(i + 2).get(1);

                ArrayList<Tuple<Integer, Integer>> solutionsX = solveDiophantine(aX, bX, cX);
                ArrayList<Tuple<Integer, Integer>> solutionsY = solveDiophantine(aY, bY, cY);

                ArrayList<Tuple<Integer, Integer>> common = new ArrayList<>();
                for (Tuple<Integer, Integer> solX : solutionsX){
                    for (Tuple<Integer, Integer> solY: solutionsY) {
                        if (solX.equals(solY)){
                            common.add(solX);
                        }
                    }
                }

                for (Tuple<Integer, Integer> solution : common) {
                    cost += 3 * solution.x + solution.y;
                }
            }
        }

        return cost;
    }

    static ArrayList<Tuple<Integer, Integer>> solveDiophantine(int a, int b, int c) {
        ArrayList<Tuple<Integer, Integer>> solutions = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (a * i + b * j == c) {
                    solutions.add(new Tuple<>(i, j));
                    System.out.println("Solution found: " + i + " " + j);
                }
            }
        }
        return solutions;
    }
}