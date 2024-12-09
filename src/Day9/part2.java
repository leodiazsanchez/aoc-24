package Day9;

import Utils.FileReader;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;

public class part2 {

    public static void main(String[] args) throws FileNotFoundException {
        FileReader<String> fr = new FileReader<>("src/Day9/input.txt", "", String.class);
        String diskMap = fr.getString();
        ArrayList<Integer> blockRepresentation = handleDiskMap(diskMap);
        System.out.println(handleBlocks(blockRepresentation));
    }

    public static ArrayList<Integer> handleDiskMap(String diskMap){
        ArrayList<Integer> arr = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < diskMap.length(); i+=2) {
            int num = Integer.parseInt(String.valueOf(diskMap.charAt(i)));

            if(num == 0){
                arr.add(id);
            }

            for (int j = 0; j < num; j++) {
                arr.add(id);
            }
            id++;

            if (i + 1 < diskMap.length()) {
                int spaces = Character.getNumericValue(diskMap.charAt(i + 1));
                for (int j = 0; j < spaces; j++) {
                    arr.add(-1);
                }
            }
        }
        return arr;
    }

    public static BigInteger handleBlocks(ArrayList<Integer> blockRepresentation){
        for (int i = blockRepresentation.size() - 1; i >= 0; i--) {
            int current = blockRepresentation.get(i);

            if (current == -1) {
                continue;
            }

            int blockEnd = i;
            while (blockEnd > 0 && blockRepresentation.get(blockEnd - 1) == current) {
                blockEnd--;
            }
            int blockSize = i - blockEnd + 1;

            for (int j = 0; j <= blockRepresentation.size() - blockSize; j++) {
                if(j >= i){
                    break;
                }

                int space = 0;
                for (int k = 0; k < blockSize; k++) {
                    if (j + k >= blockRepresentation.size() || blockRepresentation.get(j + k) != -1) {
                        break;
                    }
                    space++;
                }

                if (space >= blockSize) {
                    for (int k = 0; k < blockSize; k++) {
                        blockRepresentation.set(j + k, current);
                        blockRepresentation.set(blockEnd + k, -1);
                    }
                    break;
                }
            }

            i = blockEnd;
        }

        BigInteger checksum = BigInteger.valueOf(0);
        for (int i = 0; i < blockRepresentation.size(); i++) {
            if (blockRepresentation.get(i) != -1) {
                checksum = checksum.add(BigInteger.valueOf(i).multiply(BigInteger.valueOf(blockRepresentation.get(i))));
            }
        }

        return checksum;
    }
}
