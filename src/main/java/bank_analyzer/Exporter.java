package bank_analyzer;

import bank_analyzer.converters.Converter;
import bank_analyzer.writers.Writer;
import bank_analyzer.writers.exceptions.WriterException;

public class Exporter {
    private final Writer writer;
    private final Converter converter;

    public Exporter(Writer writer, Converter converter) {
        this.writer = writer;
        this.converter = converter;
    }

    public void export(SummaryStatistics summaryStatistics) throws WriterException {
        writer.write(converter.convert(summaryStatistics));
    }
}
