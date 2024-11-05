package qwerty0;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Account {
    protected BigDecimal balance;
    protected int accountNumber;
    protected List<String> transactions;
    protected DateTimeFormatter formatter;

    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.ZERO;
        this.transactions = new ArrayList<>();
        this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public abstract String closeAccount();

    protected void recordTransaction(String type, BigDecimal amount) {
        String timestamp = LocalDateTime.now().format(formatter);
        transactions.add(String.format("%s %s %s kr Saldo: %s kr", timestamp, type, amount, balance));
    }

    public boolean deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            return false; // Negative or zero deposits are not allowed
        }
        balance = balance.add(amount);
        recordTransaction("+", amount);
        return true;
    }

    public abstract boolean withdraw(BigDecimal amount);
}
