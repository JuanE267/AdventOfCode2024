import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day_3 {

    public static List<String[]> readingFile(){
        List<String[]> pairsOfNumbers = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("dayThreeInput.txt"));
            String line = reader.readLine();

            Pattern mulRegex = Pattern.compile("mul\\(\\-?\\d{1,3},\\-?\\d{1,3}\\)");
            Pattern commaRegex = Pattern.compile("\\-?\\d{1,3},\\-?\\d{1,3}");
            Matcher commaMatcher;

            String data = "";
            while(line != null){
                data = data.concat(line);
                line = reader.readLine();
            }
            String[] sentences = data.split("don't\\(\\)(.*?)do\\(\\)");

            for(String str: sentences){
                String[] cleanStr = str.split("don't\\(\\).*");
                for(String clean: cleanStr) {
                    Matcher mulMatcher = mulRegex.matcher(clean);
                    while (mulMatcher.find()) {
                        commaMatcher = commaRegex.matcher(mulMatcher.group());
                        if (commaMatcher.find()) {
                            String[] digits = commaMatcher.group().split(",");
                            pairsOfNumbers.add(digits);
                        }
                    }
                }
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pairsOfNumbers;
    }

    public static void main(String[] args) {
        List<String[]> pairsOfNumbers = readingFile();
        Integer sumatory = 0;


        for(String[] set: pairsOfNumbers){
            Integer i = Integer.valueOf(set[0]);
            Integer j = Integer.valueOf(set[1]);
            sumatory +=  i * j;
        }

        System.out.println("sumatory = " + sumatory);


    }
}
