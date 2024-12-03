package Day3;

import Utils.FileReader;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class part2 {
    public static void main(String[] args) throws FileNotFoundException {
        FileReader<String> fr = new FileReader<>("src/Day3/input.txt", "", String.class);
        String input = fr.getString();

        Pattern pattern = Pattern.compile("do\\(\\)|don't\\(\\)|mul\\(\\d+,\\d+\\)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(input);

        int sum = 0;
        boolean exec = true;

        while (matcher.find()) {
            switch (matcher.group()){
                case "do()":
                    exec = true;
                    break;
                case "don't()":
                    exec = false;
                    break;
                default:
                    String[] nums = matcher.group().substring(3).split(",");
                    int left = Integer.parseInt(nums[0].substring(1));
                    int right = Integer.parseInt(nums[1].substring(0, nums[1].length()-1));

                    if(exec){
                        sum += left * right;
                    }
            }
        }

        System.out.println(sum);
    }
}
