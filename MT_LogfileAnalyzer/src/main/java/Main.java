import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> logFiles = List.of("app-2025-04-01.log", "app-2025-04-02.log", "app-2025-04-03.log");

        LogAnalyzerSequential sequentialAnalyzer = new LogAnalyzerSequential();
        LogAnalyzerParallel parallelAnalyzer = new LogAnalyzerParallel();

        // Sequentielle Analyse
        long startTime = System.currentTimeMillis();
        sequentialAnalyzer.analyzeLogs(logFiles);
        long endTime = System.currentTimeMillis();
        System.out.println("Sequentielle Analyse dauerte: " + (endTime - startTime) + "ms");

        // Parallele Analyse
        startTime = System.currentTimeMillis();
        parallelAnalyzer.analyzeLogs(logFiles);
        endTime = System.currentTimeMillis();
        System.out.println("Parallele Analyse dauerte: " + (endTime - startTime) + "ms");
    }
}
