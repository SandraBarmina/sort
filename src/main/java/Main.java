import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import sort.Reader;
import sort.SortingImpl;
import java.util.Arrays;

public class Main {
    enum ContentType {
        i, s
    }

    enum SortMode {
        a, d
    }
    @Parameters(paramLabel = "FILE", index = "0", description = "The file with data to sort.") File[] files;
    @Option(names = { "-o", "--out-prefix", "Output file prefix. -o"}) String prefix;
    @Option(names = { "-c", "--content-type", "Content type (String or Integer): ${COMPLETION-CANDIDATES}"}) ContentType type;
    @Option(names = { "-s", "--sort-mode", "Sorting type (ascending or descending): ${COMPLETION-CANDIDATES}"}) SortMode sortMode;
    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message") boolean helpRequested = false;

    public static void main(String[] args) {
        Main app = CommandLine.populateCommand(new Main(), args);

        Reader reader = new Reader();

        if (app.type == null || app.prefix == null || app.sortMode == null || app.files == null || app.helpRequested) {
            CommandLine.usage(new Main(), System.out);
            return;
        }
        Arrays.stream(app.files).forEach(file -> {
            try {
                if (app.type.equals(ContentType.s)) {
                    ArrayList<String> arr = reader.readStringFile(file);
                    SortingImpl<String> sort = new SortingImpl<>();
                    if (app.sortMode.equals(SortMode.a)){
                        sort.sortAscending(arr);
                    } else {
                        sort.sortDescending(arr);
                    }
                } else {
                    ArrayList<Integer> arr = reader.readIntFile(file);
                    SortingImpl<Integer> sort = new SortingImpl<>();
                    if (app.sortMode.equals(SortMode.a)){
                        sort.sortAscending(arr);
                    } else {
                        sort.sortDescending(arr);
                    }
                }
            } catch (IOException e) {
                throw new AssertionError("Error for reading file");
            }

        });
    }

}
