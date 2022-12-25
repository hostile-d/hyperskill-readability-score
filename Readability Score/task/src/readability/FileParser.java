package readability;

import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.regex.Pattern;

@Data
public class FileParser {
    private String fileContent = "";
    private Text text;
    public FileParser(String fileName) {
        try {
            this.fileContent = Files.readString(Path.of(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.text = parseText();
    }

    private Text parseText() {
        var wordsAmount = getCountByRegex(fileContent, "\\w+\\b");
        var sentencesAmount = getCountByRegex(fileContent,"\\w+[.!?]");
        var charactersAmount = getCountByRegex(fileContent,"[^\\s\\n\\t]");
        var syllables = getSyllablesAmount();
        var syllablesAmount = syllables[0];
        var polysyllablesAmount = syllables[1];

        if (!Pattern.compile("[.!?]$").matcher(fileContent).find()) {
            sentencesAmount += 1;
        }

        wordsAmount -= getCountByRegex(fileContent,"\\d+,\\d+");

        return new Text(wordsAmount, sentencesAmount, charactersAmount, syllablesAmount, polysyllablesAmount);
    }

    private Double getCountByRegex(String text,String regex) {
        return (double) Pattern.compile(regex).matcher(text).results().count();
    }

    private Double[] getSyllablesAmount() {
        var matcher = Pattern.compile("\\w+\\b").matcher(fileContent);
        var syllablesAmount = 0d;
        var polysyllablesAmount = 0d;

        while (matcher.find()) {
            var word = matcher.group(0).toLowerCase();
            var vowelsAmount = getCountByRegex(word, "[aeiouy]+");
            if (word.split("")[word.length() - 1].equalsIgnoreCase("e") && vowelsAmount > 1) {
                vowelsAmount -= 1;
            }
            syllablesAmount += vowelsAmount == 0 ? 1 : vowelsAmount;

            if (vowelsAmount > 2) {
                polysyllablesAmount += 1;
            }
        }

        return new Double[]{syllablesAmount, polysyllablesAmount};
    }
}
