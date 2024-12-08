package Day8;

import Utils.FileReader;
import Utils.Tuple;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class part2 {

    public static void main(String[] args) throws FileNotFoundException {
        FileReader<String> fr = new FileReader<>("src/Day8/input.txt", "", String.class);
        ArrayList<ArrayList<String>> map = fr.getRows();

        HashMap<String, ArrayList<Tuple<Integer, Integer>>> antennas = getAntennas(map);

        // Using hashset to make sure only unique values get added
        HashSet<Tuple<Integer, Integer>> antinodes = new HashSet<>();

        // Try each point on the map
        for (int r = 0; r < map.size(); r++) {
            for (int c = 0; c < map.get(r).size(); c++) {
                Tuple<Integer, Integer> p = new Tuple<>(c, r);

                // For each antenna type
                for (String key : antennas.keySet()) {
                    ArrayList<Tuple<Integer, Integer>> keys = antennas.get(key);
                    for (int i = 0; i < keys.size(); i++) {

                        // Get the slope to p for one of them at a time
                        Tuple<Integer, Integer> antenna = keys.get(i);
                        double slope = calcSlope(p, antenna);

                        // Check against the others following (We do not miss any comparisons, since we would have caught it in previous iterators)
                        for (int j = i + 1; j < keys.size(); j++) {

                            Tuple<Integer, Integer> otherAntenna = keys.get(j);
                            double slope2 = calcSlope(p, otherAntenna);

                            // Check if the conditions are meet to add point to antinodes
                            if (slope == slope2) {
                                antinodes.add(p);
                            }
                        }
                    }
                }
            }
        }

        // Also add the count of a certain type of antenna to the total count if it's more than one
        int counter = 0;

        for (String key : antennas.keySet()) {
            if(antennas.get(key).size() == 1){
                return;
            }

            for (Tuple<Integer,Integer> antenna : antennas.get(key)) {
                antinodes.add(antenna);
            }
        }

        System.out.println(counter + antinodes.size());
    }

    public static HashMap<String, ArrayList<Tuple<Integer, Integer>>> getAntennas(ArrayList<ArrayList<String>> map) {
        HashMap<String, ArrayList<Tuple<Integer, Integer>>> antennas = new HashMap<>();

        for (int r = 0; r < map.size(); r++) {
            for (int c = 0; c < map.get(r).size(); c++) {
                String p = map.get(r).get(c);
                if (!p.equals(".")){
                    if(antennas.containsKey(p)){
                        antennas.get(p).add(new Tuple<>(c,r));
                    } else {
                        ArrayList<Tuple<Integer, Integer>> points = new ArrayList<>();
                        points.add(new Tuple<>(c,r));
                        antennas.put(p,points);
                    }
                }
            }
        }

        return antennas;
    }

    public static double calcSlope(Tuple<Integer, Integer> p1, Tuple<Integer, Integer> p2) {
        return (double) (p1.y - p2.y) / (p1.x - p2.x);
    }
}
