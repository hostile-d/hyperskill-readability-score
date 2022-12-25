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
        var wordsAmount = getCountByRegex("\\w+\\b");
        var sentencesAmount = getCountByRegex("\\w+[.!?]");
        var charactersAmount = getCountByRegex("[^\\s\\n\\t]");

        if (!Pattern.compile("[.!?]$").matcher(fileContent).find()) {
            sentencesAmount += 1;
        }

        wordsAmount -= getCountByRegex("\\d+,\\d+");

        return new Text(wordsAmount, sentencesAmount, charactersAmount);
    }

    private Double getCountByRegex(String regex) {
        return (double) Pattern.compile(regex).matcher(fileContent).results().count();
    }
}
