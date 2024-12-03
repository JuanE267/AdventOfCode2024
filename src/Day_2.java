import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day_2 {

    public enum Behavior {
        INCREASING,
        DECREASING,
        STATIC
    }

    public static void readingFile(List<List<Integer>> list) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("dayTwoInput.txt"));
            String line = reader.readLine();

            while (line != null) {
                String[] dataArray = line.split("\\s+");
                List<Integer> internalList = new ArrayList<>();
                for (String n : dataArray) {
                    internalList.add(Integer.parseInt(n));
                }
                list.add(internalList);
                line = reader.readLine();
            }
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean studyingBehavior(List<Integer> input) {

        if(input.size() <= 1) return true;

        Behavior behavior;
        int difference;

        if (input.get(0) > input.get(1)) {
            behavior = Behavior.DECREASING;
        } else if (input.get(0) < input.get(1)) {
            behavior = Behavior.INCREASING;
        } else {
            behavior = Behavior.STATIC;
        }

        switch (behavior) {
            case INCREASING:
                for (int i = 1; i < input.size(); i++) {
                    if (!(input.get(i - 1) < input.get(i))) {
                        return false;
                    }
                    difference = Math.abs(input.get(i) - input.get(i - 1));
                    if (difference < 1 || difference > 3) {
                        return false;
                    }
                }
                break;

            case DECREASING:
                for (int i = 1; i < input.size(); i++) {
                    if (!(input.get(i - 1) > input.get(i))) {
                        return false;
                    }
                    difference = Math.abs(input.get(i) - input.get(i - 1));
                    if (difference < 1 || difference > 3) {
                        return false;
                    }
                }
                break;
            case STATIC:
                return false;
        }

        return true;
    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        readingFile(list);

        int safeReports = 0;

        for (List<Integer> input : list) {
            if (studyingBehavior(input)) {
                safeReports++;
            } else {
                for (int i = 0; i < input.size(); i++) {
                    List<Integer> copy = new ArrayList<>(input);
                    copy.remove(i);
                    if (studyingBehavior(copy)) {
                        safeReports++;
                        break;
                    }
                }
            }
        }
        System.out.println("safeReports = " + safeReports);

    }
}