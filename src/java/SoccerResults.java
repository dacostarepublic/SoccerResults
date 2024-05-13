import java.io.*;
import java.util.*;
import java.util.regex.*;

public class SoccerResults {
    public static Map<String, Integer> teamPoints = new HashMap<>();

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide a filename as argument.");
            return;
        }
        String filename = args[0];
        processFile(filename);
        printPoints();
    }

    public static void processFile(String filename) {
        File file = new File(filename);
        if (!file.exists() || !file.canRead()) {
            System.out.println("File " + filename + " does not exist or is not readable.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = parseLine(line);
                if (parts[0] != null) {
                    updatePoints(parts[0], Integer.parseInt(parts[1]), parts[2], Integer.parseInt(parts[3]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static String[] parseLine(String line) {
        Pattern pattern = Pattern.compile("(.*?) (\\d+), (.*?) (\\d+)");
        Matcher matcher = pattern.matcher(line.trim());
        if (matcher.matches()) {
            return new String[]{matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4)};
        }
        return new String[]{null, null, null, null};
    }

    public static void updatePoints(String team1, int score1, String team2, int score2) {
        if (score1 > score2) {
            teamPoints.put(team1, teamPoints.getOrDefault(team1, 0) + 3);
            teamPoints.put(team2, teamPoints.getOrDefault(team2, 0) + 0);
        } else if (score1 < score2) {
            teamPoints.put(team1, teamPoints.getOrDefault(team1, 0) + 0);
            teamPoints.put(team2, teamPoints.getOrDefault(team2, 0) + 3);
        } else {
            teamPoints.put(team1, teamPoints.getOrDefault(team1, 0) + 1);
            teamPoints.put(team2, teamPoints.getOrDefault(team2, 0) + 1);
        }
    }

    private static void printPoints() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>(teamPoints.entrySet());
        list.sort((o1, o2) -> {
            int pointComparison = o2.getValue().compareTo(o1.getValue());
            if (pointComparison != 0) {
                return pointComparison;
            } else {
                return o1.getKey().compareTo(o2.getKey());
            }
        });
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getKey() + ", " + list.get(i).getValue() + " pts");
        }
    }
}