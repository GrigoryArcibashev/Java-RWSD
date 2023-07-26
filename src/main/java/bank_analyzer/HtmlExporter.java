package bank_analyzer;

public class HtmlExporter implements Exporter {
    @Override
    public String export(final SummaryStatistics summaryStatistics) {
        String result = "<!DOCTYPE html>\n";
        result += "<html lang='en'>\n";
        result += "<head><title>Bank Transaction Report</title></head>\n";
        result += "<body>\n";
        result += "<ul>\n";
        result += "\t<li><strong>The sum is</strong>: " + summaryStatistics.getSum() + "</li>\n";
        result += "\t<li><strong>The average is</strong>: " + summaryStatistics.getAverage() + "</li>\n";
        result += "\t<li><strong>The max is</strong>: " + summaryStatistics.getMax() + "</li>\n";
        result += "\t<li><strong>The min is</strong>: " + summaryStatistics.getMin() + "</li>\n";
        result += "</ul>\n";
        result += "</body>\n";
        result += "</html>\n";
        return result;
    }
}
