package Day6;

import Utils.FileReader;
import Utils.Tuple;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class part1 {

    public static void main(String[] args) throws FileNotFoundException {
        FileReader<String> fr = new FileReader<>("src/Day6/input.txt", "", String.class);
        ArrayList<ArrayList<String>> map = fr.getRows();

        Tuple<Integer, Integer> start = getStartPos(map);

        Direction direction = Direction.UP;

        int currentX = start.x;
        int currentY = start.y;

        Set<Tuple<Integer, Integer>> visited = new HashSet<>();

        while (inBounds(currentX, currentY, map)) {
            Tuple<Integer, Integer> currentPosition = new Tuple<>(currentX, currentY);


            if (Objects.equals(map.get(currentY).get(currentX), "#")) {
                System.out.println("Found #: r=" + currentY + ", c=" + currentX);

                // Back up a step, since we don't want the guard to stand on the '#'
                switch (direction) {
                    case UP -> currentY++;
                    case RIGHT -> currentX--;
                    case DOWN -> currentY--;
                    case LEFT -> currentX++;
                }

                direction = direction.next();

            } else {
                visited.add(currentPosition);
            }

            currentX += direction.getDx();
            currentY += direction.getDy();
        }

        System.out.println(visited.size());
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
}
