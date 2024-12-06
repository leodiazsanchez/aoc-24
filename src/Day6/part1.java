package Day6;

import Utils.FileReader;
import Utils.Tuple;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;

public class part1 {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader<String> fr = new FileReader<>("src/Day6/input.txt", "", String.class);
        ArrayList<ArrayList<String>> map = fr.getRows();

        int x = -1, y = -1;
        for (int r = 0; r < map.size(); r++) {
            for (int c = 0; c < map.get(r).size(); c++) {
              System.out.print(map.get(r).get(c));
              if((map.get(r).get(c)).equals("^")){
                  x = r;
                  y = c;
              }
            }
            System.out.print("\n");
        }

        int dirX = 0;
        int dirY = -1;
        int dirIndex = 0;

        int r = x;
        int c = y;

        ArrayList<Tuple<Integer,Integer>> uniquePos = new ArrayList<>();

        uniquePos.add(new Tuple<>(r,c));

        while (r >= 0 && r < map.size() && c >= 0 && c < map.get(r).size()) {
            Tuple<Integer,Integer> pos = new Tuple<>(r,c);
            boolean exists = false;
            for (Tuple<Integer, Integer> tuple : uniquePos) {
                if (Objects.equals(pos.x, tuple.x) && Objects.equals(pos.y, tuple.y)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                uniquePos.add(pos);
            }
            if (map.get(r).get(c).equals("#")) {
                System.out.println("Found #: r=" + r + ", c=" + c);
                uniquePos.remove(pos);
                switch (dirIndex) {
                    case 0 -> { // Up -> Right
                        dirX = 1;
                        dirY = 0;
                        dirIndex++;
                        r=r+1;
                    }
                    case 1 -> { // Right -> Down
                        dirX = 0;
                        dirY = 1;
                        dirIndex++;
                        c=c-1;
                    }
                    case 2 -> { // Down -> Left
                        dirX = -1;
                        dirY = 0;
                        dirIndex++;
                        r=r-1;
                    }
                    case 3 -> { // Left -> Up
                        dirX = 0;
                        dirY = -1;
                        dirIndex = 0;
                        c=c+1;
                    }
                }
            }

            r += dirY;
            c += dirX;
        }

        System.out.println(uniquePos.size());
    }
}
