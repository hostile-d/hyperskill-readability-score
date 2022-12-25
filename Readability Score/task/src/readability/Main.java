package readability;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        var parser = new FileParser(args[0]);
        var cli = new CLI(parser.getText());
        cli.printMainStatistics();
        cli.askForScore();
    }
}
