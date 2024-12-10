package Day10;

import Utils.FileReader;
import Utils.Tuple;

import java.io.FileNotFoundException;
import java.util.*;

public class part1 {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader<Integer> fr = new FileReader<>("src/Day10/input.txt", "", Integer.class);
        ArrayList<ArrayList<Integer>> map = fr.getRows();
        System.out.println(sumScores(map));
    }

    // Direction vectors
    static int dRow[] = { -1, 0, 1, 0 };
    static int dCol[] = { 0, 1, 0, -1 };

    static int BFS(ArrayList<ArrayList<Integer>> map, Tuple<Integer,Integer> start)
    {
        int counter = 0;
        Set<Tuple<Integer, Integer>> visited = new HashSet<>();
        // Stores indices of the matrix cells
        Queue<Tuple<Integer,Integer>> q = new LinkedList<>();

        // Mark the starting cell as visited
        // and push it into the queue
        q.add(start);
        visited.add(start);

        // Iterate while the queue
        // is not empty
        while (!q.isEmpty())
        {
            Tuple<Integer,Integer> cell = q.peek();
            int x = cell.x;
            int y = cell.y;

            //System.out.print(map.get(y).get(x) + " ");

            q.remove();

            int current = map.get(y).get(x);
            if(current == 9){
                counter++;
            }

            // Go to the adjacent cells
            for(int i = 0; i < 4; i++)
            {
                int adjx = x + dRow[i];
                int adjy = y + dCol[i];

                Tuple<Integer,Integer> adj = new Tuple<>(adjx,adjy);

                if (inBounds(adjx, adjy, map) && !visited.contains(adj))
                {
                    int next = map.get(adjy).get(adjx);
                    if(next == current+1) {
                        q.add(adj);
                        visited.add(adj);
                    }
                }
            }
        }
        return counter;
    }

    private static boolean inBounds(int x, int y, ArrayList<ArrayList<Integer>> map) {
        return y >= 0 && y < map.size() && x >= 0 && x < map.get(y).size();
    }

    static int sumScores(ArrayList<ArrayList<Integer>> map){
        int counter = 0;
        for (int y = 0; y < map.size(); y++) {
            for (int x = 0; x < map.get(y).size(); x++) {
                if (map.get(y).get(x) == 0){
                    Tuple<Integer,Integer> p = new Tuple<>(x,y);
                    System.out.println("Found zero at: " + p);
                    counter += BFS(map,p);
                }
            }
        }
        return counter;
    }
}
