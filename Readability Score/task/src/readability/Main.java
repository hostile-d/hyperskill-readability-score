package readability;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var line = scanner.nextLine();

        var wordsAmount = (double) Pattern.compile("\\w+\\b").matcher(line).results().count();
        var sentencesAmount = (double) Pattern.compile("\\w+[.!?]").matcher(line).results().count();

        if (!Pattern.compile("[.!?]$").matcher(line).find()) {
            sentencesAmount += 1;
        }

        var avgWordsPerSentence = wordsAmount / sentencesAmount;

        System.out.println(avgWordsPerSentence > 10d ? "HARD" : "EASY");
    }
}
