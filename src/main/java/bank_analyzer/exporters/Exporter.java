package bank_analyzer.exporters;

import bank_analyzer.SummaryStatistics;

public interface Exporter {
    String export(SummaryStatistics summaryStatistics);
}
