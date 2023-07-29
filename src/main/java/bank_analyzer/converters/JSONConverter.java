package bank_analyzer.converters;

import bank_analyzer.SummaryStatistics;

import java.util.ArrayList;
import java.util.List;

public class JSONConverter implements Converter {
    @Override
    public List<String> convert(SummaryStatistics summaryStatistics) {
        List<String> result = new ArrayList<>();
        result.add("{\n");
        result.add("\t\"sum\":" + summaryStatistics.getSum() + ",\n");
        result.add("\t\"average\":" + summaryStatistics.getAverage() + ",\n");
        result.add("\t\"max\":" + summaryStatistics.getMax() + ",\n");
        result.add("\t\"min\":" + summaryStatistics.getMin() + "\n");
        result.add("}\n");
        return result;
    }
}
