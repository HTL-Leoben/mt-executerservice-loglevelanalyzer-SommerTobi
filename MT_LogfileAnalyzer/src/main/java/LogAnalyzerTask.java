import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class LogAnalyzerTask implements Callable<Map<String, Integer>> {
    private final String fileName;

    public LogAnalyzerTask(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Map<String, Integer> call() {
        try {
            Path path = Paths.get(fileName);
            List<String> lines = Files.readAllLines(path);
            return LogUtils.countLogLevels(lines);
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei " + fileName + ": " + e.getMessage());
        }
        return Map.of();
    }
}
