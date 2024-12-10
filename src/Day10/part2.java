package Day10;

import Utils.FileReader;
import Utils.Tuple;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;

public class part2 {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader<Integer> fr = new FileReader<>("src/Day10/input.txt", "", Integer.class);
        ArrayList<ArrayList<Integer>> map = fr.getRows();
        System.out.println(sumScores(map));
    }

    // Direction vectors
    static int dRow[] = { -1, 0, 1, 0 };
    static int dCol[] = { 0, 1, 0, -1 };

    static int DFS(ArrayList<ArrayList<Integer>> map, Tuple<Integer, Integer> start) {
        int counter = 0;

        HashSet<Tuple<Integer, Integer>> visited = new HashSet<>();
        Stack<Tuple<Integer, Integer>> stack = new Stack<>();

        // Initialize the stack with the starting cell and an empty path
        stack.push(start);

        while (!stack.isEmpty()) {
            Tuple<Integer, Integer> curr = stack.pop();

            int x = curr.x;
            int y = curr.y;

            // Skip invalid or already visited nodes
            if (!inBounds(x, y, map) || visited.contains(curr))
                continue;

            // Mark the node as visited
            visited.add(curr);

            int currentValue = map.get(y).get(x);

            if (currentValue == 9) {
                visited.remove(curr); // Allow revisiting for other paths
                counter++;
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int adjx = x + dRow[i];
                int adjy = y + dCol[i];

                if (inBounds(adjx, adjy, map) && map.get(adjy).get(adjx) == currentValue + 1) {
                    Tuple<Integer, Integer> next = new Tuple<>(adjx, adjy);
                    stack.push(next);
                }
            }

            // Allow revisiting for other paths
            visited.remove(curr);
        }

        return counter;
    }

    static boolean inBounds(int x, int y, ArrayList<ArrayList<Integer>> map) {
        return y >= 0 && y < map.size() && x >= 0 && x < map.get(0).size();
    }

    static int sumScores(ArrayList<ArrayList<Integer>> map){
        int counter = 0;
        for (int y = 0; y < map.size(); y++) {
            for (int x = 0; x < map.get(y).size(); x++) {
                if (map.get(y).get(x) == 0){
                    Tuple<Integer,Integer> p = new Tuple<>(x,y);
                    counter += DFS(map,p);
                }
            }
        }
        return counter;
    }
}
