package Day3;

import Utils.FileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class part1 {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader<String> fr = new FileReader<>("src/Day3/input.txt", " ", String.class);
        ArrayList<ArrayList<String>> rows = fr.getRows();

        Pattern pattern = Pattern.compile("mul\\(\\d+,\\d+\\)", Pattern.CASE_INSENSITIVE);

        String input = "";
        for (ArrayList<String> row: rows){
            for (String str: row) {
                input += str;
            }
        }

        Matcher matcher = pattern.matcher(input);
        int sum = 0;
        while (matcher.find()) {
            String[] nums = matcher.group().substring(3).split(",");
            int left = Integer.parseInt(nums[0].substring(1));
            int right = Integer.parseInt(nums[1].substring(0, nums[1].length()-1));
            sum += left * right;
        }

        System.out.println(sum);
    }
}
