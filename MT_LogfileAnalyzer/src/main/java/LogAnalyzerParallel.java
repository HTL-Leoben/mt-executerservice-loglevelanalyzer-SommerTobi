import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class LogAnalyzerParallel {
    public void analyzeLogs(List<String> logFiles) {
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Future<Map<String, Integer>>> futures;

        try {
            futures = executor.invokeAll(logFiles.stream().map(LogAnalyzerTask::new).toList());
            executor.shutdown();

            Map<String, Integer> totalCounts = new HashMap<>();
            for (Future<Map<String, Integer>> future : futures) {
                try {
                    Map<String, Integer> result = future.get();
                    result.forEach((key, value) -> totalCounts.merge(key, value, Integer::sum));
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Gesamtergebnis (parallel): " + totalCounts);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
