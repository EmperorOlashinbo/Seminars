package mdatah4;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class Account {
    protected int accountNumber;
    protected BigDecimal balance;
    protected List<String> transactions;

    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
        this.balance = BigDecimal.ZERO;
        this.transactions = new ArrayList<>();
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

    public boolean deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) return false;
        balance = balance.add(amount);
        addTransaction(amount, balance);
        return true;
    }

    public boolean withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0 || balance.subtract(amount).compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }
        balance = balance.subtract(amount);
        addTransaction(amount.negate(), balance);
        return true;
    }

    public abstract BigDecimal closeAccount();

    protected void addTransaction(BigDecimal amount, BigDecimal balanceAfter) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        transactions.add(timestamp + " " + formatCurrency(amount) + " Saldo: " + formatCurrency(balanceAfter));
    }

    // Helper method to format currency
    protected String formatCurrency(BigDecimal value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.forLanguageTag("sv-SE"));
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator(' ');
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
        return df.format(value) + " kr";
    }

    public abstract String getAccountType();
}
