package Day6;

import Utils.FileReader;
import Utils.Tuple;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class part2 {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader<String> fr = new FileReader<>("src/Day6/input.txt", "", String.class);
        ArrayList<ArrayList<String>> map = fr.getRows();

        int counter = 0;

        for (int r = 0; r < map.size(); r++) {
            for (int c = 0; c < map.get(r).size(); c++) {
                Tuple<Integer, Integer> start = getStartPos(map);
                System.out.println("Placing obstacle on " + c + "," + r);

                if (!start.equals(new Tuple<>(c, r))) {
                    String prev = map.get(r).get(c);
                    map.get(r).set(c, "#");
                    counter += search(map,start);
                    map.get(r).set(c, prev);
                }
            }
        }

        System.out.println(counter);
    }

    private static Tuple<Integer, Integer> getStartPos(ArrayList<ArrayList<String>> map) {
        for (int r = 0; r < map.size(); r++) {
            for (int c = 0; c < map.get(r).size(); c++) {
                if (Objects.equals(map.get(r).get(c), "^")) {
                    return new Tuple<>(c, r);
                }
            }
        }
        throw new IllegalArgumentException("Starting position '^' not found in the map.");
    }

    private static boolean inBounds(int x, int y, ArrayList<ArrayList<String>> map) {
        return y >= 0 && y < map.size() && x >= 0 && x < map.get(y).size();
    }

    enum Direction {
        UP(0, -1),
        RIGHT(1, 0),
        DOWN(0, 1),
        LEFT(-1, 0);

        private final int dx;
        private final int dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        public int getDx() {
            return dx;
        }

        public int getDy() {
            return dy;
        }

        public Direction next() {
            return values()[(this.ordinal() + 1) % values().length];
        }
    }

    static void printMap(ArrayList<ArrayList<String>> map){
        for (ArrayList<String> r: map) {
            for (String c : r) {
                System.out.print(c);
            }
            System.out.print("\n");
        }
    }

    static int search(ArrayList<ArrayList<String>> map,Tuple<Integer, Integer> start ){

        Direction direction = Direction.UP;

        int currentX = start.x;
        int currentY = start.y;

        Set<Tuple<Tuple<Integer,Integer>, Direction>> visited = new HashSet<>();

        while (inBounds(currentX, currentY, map)) {
            Tuple<Integer, Integer> currentPosition = new Tuple<>(currentX, currentY);

            if (Objects.equals(map.get(currentY).get(currentX), "#")) {

                // Back up a step, since we don't want the guard to stand on the '#'
                switch (direction) {
                    case UP -> currentY++;
                    case RIGHT -> currentX--;
                    case DOWN -> currentY--;
                    case LEFT -> currentX++;
                }

                direction = direction.next();

            } else if (visited.contains(new Tuple<>(currentPosition,direction))){
                return 1;
            } else {
                visited.add(new Tuple<>(currentPosition,direction));
            }

            currentX += direction.getDx();
            currentY += direction.getDy();
        }

        return 0;
    }
}
