package reader;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Reader {

    /**
     * Reads files containing strings
     *
     * @param file - file containing strings
     * @return List with strings read from file
     * @throws IOException File reading error
     */
    public ArrayList<String> readStringFile (File file) throws IOException {
        return Files.lines(file.toPath(), StandardCharsets.UTF_8)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Reads files containing numbers
     *
     * @param file - file containing numbers
     * @return List with numbers read from file
     * @throws IOException File reading error
     */
    public ArrayList<Integer> readIntFile (File file) throws IOException {
        try {
            return Files.lines(file.toPath())
                    .map(Integer::parseInt)
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (NumberFormatException ex) {
            throw new NumberFormatException(String.format("The file \"%s\" contains fields other than numbers", file.getName()));
        }
    }
}
