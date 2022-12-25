package readability;

import java.io.File;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;

import static java.util.Map.entry;

public class TextAnalyzer {
    private final Text text;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private final Map<Integer, String> result = Map.ofEntries(
            entry(1,"5-6"),
            entry(2,"6-7"),
            entry(3,"7-8"),
            entry(4,"8-9"),
            entry(5, "9-10"),
            entry(6,"10-11"),
            entry(7,"11-12"),
            entry(8,"12-13"),
            entry(9,"13-14"),
            entry(10,"14-15"),
            entry(11,"15-16"),
            entry(12,"16-17"),
            entry(13,"17-18"),
            entry(14,"18-22")
    );

    public TextAnalyzer(Text text) {
        this.text = text;
    }

    public void printStatistics() {
        var characters = text.getCharacters();
        var words = text.getWords();
        var sentences = text.getSentences();
        var ari = calculateARI(characters, words, sentences);
        var range = result.get((int) (double) Math.ceil(ari));
        df.setRoundingMode(RoundingMode.DOWN);

        System.out.printf("Words: %s\n", words.intValue());
        System.out.printf("Sentences: %s\n", sentences.intValue());
        System.out.printf("Characters: %s\n", characters.intValue());
        System.out.printf("The score is: %s\n", df.format(ari));
        System.out.printf("This text should be understood by %s year-olds.\n", range);
    }

    private Double calculateARI(double characters, double words, double sentences) {
        return 4.71d * (characters/words) + 0.5d * (words/sentences) - 21.43d;
    }
}
