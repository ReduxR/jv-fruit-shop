package core.generator;

import java.util.Map;

public interface ReportGenerator {
    String generateReport(Map<String, Integer> data);
}
