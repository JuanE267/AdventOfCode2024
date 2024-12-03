import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day_1 {

    public static void readingFile(List<Integer> leftList, List<Integer> rightList){
        try{
            BufferedReader reader = new BufferedReader(new FileReader("dayOneInput.txt"));

            String line = reader.readLine();
            while(line != null){
                String[]   values = line.split("\\s+");
                Integer firstValue =  Integer.parseInt(values[0]);
                Integer secondValue = Integer.parseInt(values[1]);

                leftList.add(firstValue);
                rightList.add(secondValue);

                line = reader.readLine();
            }
            reader.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static int findingSmallerNumber(List<Integer> list){
        int min = list.getFirst();
        for (Integer n : list) {
            min = (n > min) ? min : n;
        }
        return min;
    }

    public static int measuringDistance(int x, int y){
        return Math.abs((x-y));
    }

    public static int countingOcurrences(Integer number, List<Integer> rightList){
        int ocurrences = 0;
        for(Integer n : rightList){
            if(n.equals(number)){
                ocurrences++;
            }
        }
        return ocurrences;
    }

    public static void main(String[] args) {

        // PART 1 -------------------------------------------------------------------------------------------------

        /*
        Pair up the smallest number in the left list with the smallest number in the right list,
        then the second-smallest left number with the second-smallest right number, and so on.
        Within each pair, figure out how far apart the two numbers are; you'll need to add up all of those distances.
        */

        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();

        readingFile(leftList, rightList);

        int totalDistance = 0;

        while(!leftList.isEmpty() || !rightList.isEmpty()){

            int first = findingSmallerNumber(leftList);
            int second = findingSmallerNumber(rightList);
            int distance = measuringDistance(first, second);
            totalDistance += distance;

            leftList.remove(Integer.valueOf(first));
            rightList.remove(Integer.valueOf(second));
        }

        System.out.println("total distance between lists = " + totalDistance);

        // PART 2 ------------------------------------------------------------------------------------------------

        /*
        This time, you'll need to figure out exactly how often each number from the left list appears in the right list.
        Calculate a total similarity score by adding up each number in the left list after multiplying it by the number of times that number appears in the right list.
        */

        readingFile(leftList, rightList);

        int similarity = 0;
        for(Integer n: leftList){
            similarity += n * countingOcurrences(n, rightList);
        }

        System.out.println("similarity between lists = " + similarity);

    }
}
