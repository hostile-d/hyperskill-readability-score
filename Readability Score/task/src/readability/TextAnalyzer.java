package readability;

import lombok.Data;

import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Scanner;

import static java.util.Map.entry;

@Data
public class TextAnalyzer {
    private final Text text;
    private final Map<Integer, String> ARIResult = Map.ofEntries(
            entry(1,"6"),
            entry(2,"7"),
            entry(3,"8"),
            entry(4,"9"),
            entry(5, "10"),
            entry(6,"11"),
            entry(7,"12"),
            entry(8,"13"),
            entry(9,"14"),
            entry(10,"15"),
            entry(11,"16"),
            entry(12,"17"),
            entry(13,"18"),
            entry(14,"22")
    );

    public TextAnalyzer(Text text) {
        this.text = text;
    }

    public Double calculateARI() {
        double characters = text.getCharacters();
        double words = text.getWords();
        double sentences = text.getSentences();

        return 4.71d * (characters / words) + 0.5d * (words / sentences) - 21.43d;
    }

    public Double calculateFK() {
        double words = text.getWords();
        double sentences = text.getSentences();
        double syllables = text.getSyllables();

        return 0.39d * (words / sentences) + 11.8d * (syllables / words) - 15.59d;
    }

    public Double calculateSMOG(){
        double sentences = text.getSentences();
        double polysyllables = text.getPolysyllables();

        return 1.043d * Math.sqrt(polysyllables * (30 / sentences)) + 3.1291d;
    }

    public Double calculateCL(){
        double characters = text.getCharacters();
        double words = text.getWords();
        double sentences = text.getSentences();

        var avgCharsPer100Words = characters / words * 100;
        var avgSentPer100Words = sentences / words * 100;

        return 0.0588d * avgCharsPer100Words - 0.296d * avgSentPer100Words - 15.8;
    }
}
