import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogUtils {
    private static final List<String> LOG_LEVELS = List.of("TRACE", "DEBUG", "INFO", "WARN", "ERROR");

    public static Map<String, Integer> countLogLevels(List<String> lines) {
        Map<String, Integer> counts = new HashMap<>();
        for (String line : lines) {
            for (String level : LOG_LEVELS) {
                if (line.contains(level)) {
                    counts.merge(level, 1, Integer::sum);
                    break;
                }
            }
        }
        return counts;
    }
}
