package bank_analyzer;

//TODO переделать на Predicate
@FunctionalInterface
public interface BankTransactionFilter {
    boolean test(BankTransaction bankTransaction);
}
