package readability;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import static java.util.Map.entry;

public class CLI {
    private TextAnalyzer textAnalyzer;
    private Text text;
    private final DecimalFormat df = new DecimalFormat("0.00");
    private Map<String, String> indexNames = Map.ofEntries(
            entry("ARI","Automated Readability Index"),
            entry("FK","Flesch–Kincaid readability tests"),
            entry("SMOG","Simple Measure of Gobbledygook"),
            entry("CL","Coleman–Liau index")
    );
    public CLI(Text text) {
        this.text = text;
        this.textAnalyzer = new TextAnalyzer(text);
        df.setRoundingMode(RoundingMode.DOWN);
    }

    public void printMainStatistics() {
        System.out.printf("Words: %s\n", text.getWords().intValue());
        System.out.printf("Sentences: %s\n", text.getSentences().intValue());
        System.out.printf("Characters: %s\n", text.getCharacters().intValue());
        System.out.printf("Syllables: %s\n", text.getSyllables().intValue());
        System.out.printf("Polysyllables: %s\n", text.getPolysyllables().intValue());
    }

    public void askForScore() {
        System.out.println("Enter the score you want to calculate (ARI, FK, SMOG, CL, all):");
        var scanner = new Scanner(System.in);
        switch (scanner.nextLine()) {
            case "ARI": {
                printIndex(textAnalyzer.calculateARI(), "ARI");
                break;
            }
            case "FK": {
                printIndex(textAnalyzer.calculateFK(), "FK");
                break;
            }
            case "SMOG": {
                printIndex(textAnalyzer.calculateSMOG(), "SMOG");
                break;
            }
            case "CL": {
                printIndex(textAnalyzer.calculateCL(), "CL");
                break;
            }
            case "all": {
                printIndex(textAnalyzer.calculateARI(), "ARI");
                printIndex(textAnalyzer.calculateFK(), "FK");
                printIndex(textAnalyzer.calculateSMOG(), "SMOG");
                printIndex(textAnalyzer.calculateCL(), "CL");
                break;
            }
            default: {
                askForScore();
            }
        }
        scanner.close();
    }

    private void printIndex(Double indexVal, String indexName) {
        var range = textAnalyzer.getARIResult().get((int) (double) Math.ceil(indexVal));
        System.out.printf("%s: %s (about %s-year-olds).\n", indexNames.get(indexName), df.format(indexVal), range);
    }
}
