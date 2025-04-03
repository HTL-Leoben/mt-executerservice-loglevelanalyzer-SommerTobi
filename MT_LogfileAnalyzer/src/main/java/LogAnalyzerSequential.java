import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogAnalyzerSequential {
    public void analyzeLogs(List<String> logFiles) {
        Map<String, Integer> totalCounts = new HashMap<>();
        for (String file : logFiles) {
            try {
                Path path = Paths.get(file);
                List<String> lines = Files.readAllLines(path);
                Map<String, Integer> fileCounts = LogUtils.countLogLevels(lines);
                System.out.println("Ergebnisse für Datei " + file + ": " + fileCounts);
                fileCounts.forEach((key, value) -> totalCounts.merge(key, value, Integer::sum));
            } catch (IOException e) {
                System.err.println("Fehler beim Lesen der Datei " + file + ": " + e.getMessage());
            }
        }
        System.out.println("Gesamtergebnis: " + totalCounts);
    }
}
