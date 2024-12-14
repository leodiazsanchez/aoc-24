package Day12;

import Utils.FileReader;
import Utils.Tuple;
import java.io.FileNotFoundException;
import java.util.*;

public class part1 {

    static ArrayList<Tuple<String,Tuple<Integer, Integer>>> gardenStuff = new ArrayList<>();
    static Set<Tuple<Integer, Integer>> visited = new HashSet<>();
    public static void main(String[] args) throws FileNotFoundException {
        FileReader<String> fr = new FileReader<>("src/Day12/input.txt", "", String.class);
        ArrayList<ArrayList<String>> map = fr.getRows();
        for (int y = 0; y < map.size(); y++) {
            for (int x = 0; x < map.get(y).size(); x++) {
                Tuple<Integer,Integer> p = new Tuple<>(x,y);
                BFS(map,p);
            }
        }
        System.out.println(processGardenStuff(gardenStuff));
    }

    static int processGardenStuff(ArrayList<Tuple<String,Tuple<Integer, Integer>>> gardenStuff){
        int sum = 0;
        for (Tuple<String,Tuple<Integer, Integer>> t : gardenStuff){
            sum += t.y.x * t.y.y;
        }
        return sum;
    }

    // Direction vectors
    static int dRow[] = { -1, 0, 1, 0 };
    static int dCol[] = { 0, 1, 0, -1 };

    static void BFS(ArrayList<ArrayList<String>> map, Tuple<Integer,Integer> start)
    {
        String startValue = map.get(start.y).get(start.x);
        int area = 0;
        int edges = 0;

        Queue<Tuple<Integer,Integer>> q = new LinkedList<>();


        // Mark the starting cell as visited
        // and push it into the queue
        if(visited.contains(start)){
            return;
        }

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
            area++;

            int localEdges = 0;

            for (int i = 0; i < 4; i++) {

                int adjx = x + dRow[i];
                int adjy = y + dCol[i];

                Tuple<Integer, Integer> adj = new Tuple<>(adjx, adjy);

                if (!inBounds(adjx, adjy, map) || !map.get(adjy).get(adjx).equals(startValue)) {
                    localEdges++;
                } else if (!visited.contains(adj)) {
                    q.add(adj);
                    visited.add(adj);
                }
            }
            edges += localEdges;
        }

        Tuple values = new Tuple<>(area, edges);
        Tuple record = new Tuple<>(startValue,values);
        gardenStuff.add(record);
    }

    private static boolean inBounds(int x, int y, ArrayList<ArrayList<String>> map) {
        return y >= 0 && y < map.size() && x >= 0 && x < map.get(y).size();
    }

}
