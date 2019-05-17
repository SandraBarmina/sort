import error.FileProcessingError;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Optional;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import reader.Reader;
import sort.SortingImpl;
import sort.SortingType;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    enum ContentType {i, s}
    enum SortMode {a, d}

    @Parameters(paramLabel = "FILE", arity = "1..", index = "0", description = "The file with data to sort.") File inputFile;
    @Option(names = {"-o", "--out-prefix"}, description = "Output file prefix.", required = true) String prefix;
    @Option(names = {"-c", "--content-type"}, description = "Content type (String or Integer). Valid values: ${COMPLETION-CANDIDATES}", required = true) ContentType type;
    @Option(names = {"-s", "--sort-mode"}, description = "Sorting type (ascending or descending). Valid values: ${COMPLETION-CANDIDATES}", required = true) SortMode sortMode;
    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display a help message") boolean helpRequested = false;

    public static void main(String[] args) {
        Main app = CommandLine.populateCommand(new Main(), args);

        if (app.helpRequested) {
            CommandLine.usage(new Main(), System.out);
            return;
        }

        SortingType sortingType = app.sortMode.equals(SortMode.a) ? SortingType.ASCENDING : SortingType.DESCENDING;

        Runnable task = () -> {
            String absolutePath = app.inputFile.getAbsolutePath();
            File[] files = Optional.ofNullable(app.inputFile.listFiles())
                            .orElseThrow(() -> new FileProcessingError("Incorrect directory: " + absolutePath));

            Arrays.stream(files).filter(File::isFile).forEach(file -> {

                String absoluteOutputFilePath = file.getParentFile().getAbsolutePath()
                                        + File.separator + app.prefix + file.getName();
                try {
                    Path path = Paths.get(absoluteOutputFilePath);
                    Reader reader = new Reader();

                    if (app.type.equals(ContentType.s)) {
                        ArrayList<String> arr = reader.readStringFile(file);
                        new SortingImpl<String>().sort(arr, sortingType);
                        Files.write(path, arr);
                    } else {
                        ArrayList<Integer> arrInt = reader.readIntFile(file);
                        new SortingImpl<Integer>().sort(arrInt, sortingType);
                        Files.write(path, arrInt.stream().map(String::valueOf).collect(Collectors.toList()));
                    }
                } catch (IOException e) {
                    throw new FileProcessingError("Error writing file: " + absoluteOutputFilePath, e);
                }
            });
        };

        Thread thread = new Thread(task);
        thread.start();

        Thread thread2 = new Thread(task);
        thread2.start();
    }
}
