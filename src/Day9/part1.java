package Day9;

import Utils.FileReader;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;

public class part1 {

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
        for (int i = blockRepresentation.size()-1; i>0 ; i--) {
            if(blockRepresentation.get(i) != -1){
                int temp = blockRepresentation.get(i);
                for (int j = 0; j < blockRepresentation.size(); j++) {
                    if(j >= i){
                        break;
                    }
                    if(blockRepresentation.get(j) == -1){
                        blockRepresentation.set(j,temp);
                        blockRepresentation.set(i,-1);
                        break;
                    }
                }
            }
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
