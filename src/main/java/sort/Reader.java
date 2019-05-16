package sort;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Reader {

    public ArrayList<String> readStringFile (File file) throws IOException {
        return Files.lines(file.toPath(), StandardCharsets.UTF_8)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Integer> readIntFile (File file) throws IOException {
        return Files.lines(file.toPath())
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    //move to
    public ArrayList<String> writeStringFile (File file) throws IOException {
        return Files.lines(file.toPath(), StandardCharsets.UTF_8)
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
