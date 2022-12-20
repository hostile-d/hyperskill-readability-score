package readability;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var line = scanner.nextLine();
        System.out.println(line.length() > 100 ? "HARD" : "EASY");
    }
}
