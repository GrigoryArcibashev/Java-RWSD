package bank_analyzer.converters;

import bank_analyzer.SummaryStatistics;

import java.util.List;

public interface Converter {
    List<String> convert(SummaryStatistics summaryStatistics);
}
