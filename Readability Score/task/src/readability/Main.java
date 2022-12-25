package readability;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        var parser = new FileParser(args[0]);
        var analyzer = new TextAnalyzer(parser.getText());
        analyzer.printStatistics();
    }
}
