import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import reader.Reader;
import sort.SortingImpl;
import sort.SortingType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    enum ContentType {i, s}
    enum SortMode {a, d}

    @Parameters(paramLabel = "FILE", index = "0", description = "The file with data to sort.") File inputFile;
    @Option(names = { "-o", "--out-prefix", "Output file prefix. -o"}) String prefix;
    @Option(names = { "-c", "--content-type", "Content type (String or Integer): "}) ContentType type;
    @Option(names = { "-s", "--sort-mode", "Sorting type (ascending or descending): "}) SortMode sortMode;
    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message") boolean helpRequested = false;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Main app = CommandLine.populateCommand(new Main(), args);

        Reader reader = new Reader();

        if (app.type == null || app.prefix == null || app.sortMode == null || app.inputFile == null || app.helpRequested) {
            CommandLine.usage(new Main(), System.out);
            return;
        }

        Runnable task = () -> {
            System.out.printf("%s started... \n", Thread.currentThread().getName());
            if (app.inputFile.listFiles() == null) {
                throw new AssertionError("Incorrect directory: " + app.inputFile.getAbsolutePath());
            }
            if (app.inputFile.listFiles().length <= 0) {
                throw new AssertionError("Directory is empty: " + app.inputFile.getAbsolutePath());
            }
            Arrays.stream(app.inputFile.listFiles()).filter(File::isFile).forEach(file -> {
                try {
                    System.out.printf("%s work with file... %s\n", Thread.currentThread().getName(), file.getName());
                    List<String> res;

                    SortingImpl sort = new SortingImpl();
                    SortingType sortingType = app.sortMode.equals(SortMode.a) ? SortingType.ASCENDING : SortingType.DESCENDING;

                    if (app.type.equals(ContentType.s)) {
                        ArrayList<String> arr = reader.readStringFile(file);
                        sort.sort(arr, sortingType);
                        res = arr;
                    } else {
                        ArrayList<Integer> arrInt = reader.readIntFile(file);
                        sort.sort(arrInt, sortingType);
                        res = arrInt.stream().map(i -> i.toString()).collect(Collectors.toList());
                    }
                    Path path = Paths.get(file.getParentFile().getAbsolutePath() + "\\" + app.prefix + file.getName());
                    Files.write(path, res);
                } catch (IOException e) {
                    throw new AssertionError("Error for reading file");
                }
            });
            System.out.printf("%s stopped... \n", Thread.currentThread().getName());
        };

        Thread thread = new Thread(task);
        thread.start();
        Thread thread2 = new Thread(task);
        thread2.start();

        try {
            thread.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("программа выполнялась " + timeSpent);
    }
}
