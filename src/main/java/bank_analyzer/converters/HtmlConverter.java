package bank_analyzer.converters;

import bank_analyzer.SummaryStatistics;

import java.util.ArrayList;
import java.util.List;

public class HtmlConverter implements Converter {
    @Override
    public List<String> convert(final SummaryStatistics summaryStatistics) {
        List<String> result = new ArrayList<>();
        result.add("<!DOCTYPE html>\n");
        result.add("<html lang='en'>\n");
        result.add("<head><title>Bank Transaction Report</title></head>\n");
        result.add("<body>\n");
        result.add("<ul>\n");
        result.add("\t<li><strong>The sum is</strong>: " + summaryStatistics.getSum() + "</li>\n");
        result.add("\t<li><strong>The average is</strong>: " + summaryStatistics.getAverage() + "</li>\n");
        result.add("\t<li><strong>The max is</strong>: " + summaryStatistics.getMax() + "</li>\n");
        result.add("\t<li><strong>The min is</strong>: " + summaryStatistics.getMin() + "</li>\n");
        result.add("</ul>\n");
        result.add("</body>\n");
        result.add("</html>\n");
        return result;
    }
}
