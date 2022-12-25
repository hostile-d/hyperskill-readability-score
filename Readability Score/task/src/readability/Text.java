package readability;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Text {
    private Double words;
    private Double sentences;
    private Double characters;
}
