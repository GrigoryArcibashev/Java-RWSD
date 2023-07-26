package com.iteratrlearning.shu_book.chapter_03;

import com.iteratrlearning.shu_book.chapter_02.BankStatementCSVParser;
import com.iteratrlearning.shu_book.chapter_02.BankStatementParser;
import com.iteratrlearning.shu_book.chapter_02.BankTransaction;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankTransactionCSVParserTest {

    private final BankStatementParser statementParser = new BankStatementCSVParser();

    @Test
    public void shouldParseOneCorrectLine() {
        String line = "30-01-2017,-50,Tesco";

        com.iteratrlearning.shu_book.chapter_02.BankTransaction result = statementParser.parseFrom(line);

        com.iteratrlearning.shu_book.chapter_02.BankTransaction expected = new com.iteratrlearning.shu_book.chapter_02.BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco");
        Assert.assertEquals(expected.getDate(), result.getDate());
        Assert.assertEquals(expected.getAmount(), result.getAmount(), 0.0d);
        Assert.assertEquals(expected.getDescription(), result.getDescription());
    }

    @Test
    public void shouldParseSeveralCorrectLines() {
        String[] lines = {
                "30-01-2017,-50,Tesco",
                "28-02-2018,1000,Tesco",
                "30-03-2019,-250,Tesco"
        };

        com.iteratrlearning.shu_book.chapter_02.BankTransaction[] result = statementParser
                .parseLinesFrom(List.of(lines))
                .toArray(new com.iteratrlearning.shu_book.chapter_02.BankTransaction[0]);

        com.iteratrlearning.shu_book.chapter_02.BankTransaction[] expected = {
                new com.iteratrlearning.shu_book.chapter_02.BankTransaction(LocalDate.of(2017, Month.JANUARY, 30), -50, "Tesco"),
                new com.iteratrlearning.shu_book.chapter_02.BankTransaction(LocalDate.of(2018, Month.FEBRUARY, 28), 1000, "Tesco"),
                new BankTransaction(LocalDate.of(2019, Month.MARCH, 30), -250, "Tesco"),
        };
        Assert.assertArrayEquals(expected, result);
    }

}
