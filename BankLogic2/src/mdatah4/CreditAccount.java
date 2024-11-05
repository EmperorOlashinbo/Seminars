package mdatah4;

import java.math.BigDecimal;

public class CreditAccount extends Account {
    private static final BigDecimal CREDIT_LIMIT = new BigDecimal("-5000");
    private static final BigDecimal POSITIVE_INTEREST_RATE = new BigDecimal("0.011");  // 1.1%
    private static final BigDecimal NEGATIVE_INTEREST_RATE = new BigDecimal("0.05");   // 5%

    public CreditAccount(int accountNumber) {
        super(accountNumber);
    }

    @Override
    public boolean withdraw(BigDecimal amount) {
        if (balance.subtract(amount).compareTo(CREDIT_LIMIT) >= 0) {
            balance = balance.subtract(amount);
            addTransaction(amount.negate(), balance);
            return true;
        }
        return false;
    }

    @Override
    public BigDecimal closeAccount() {
        // Apply interest based on whether the balance is positive or negative
        if (balance.compareTo(BigDecimal.ZERO) < 0) {
            BigDecimal interest = balance.multiply(NEGATIVE_INTEREST_RATE);
            balance = balance.add(interest);  // Apply negative interest to a negative balance
        } else {
            BigDecimal interest = balance.multiply(POSITIVE_INTEREST_RATE);
            balance = balance.add(interest);  // Apply positive interest to a positive balance
        }
        BigDecimal finalBalance = balance;
        balance = BigDecimal.ZERO;  // Set balance to 0 after account closure
        return finalBalance;
    }

    @Override
    public String getAccountType() {
        if (balance.compareTo(BigDecimal.ZERO) >= 0) {
            return "Kreditkonto 1,1 %";  // Positive balance
        } else {
            return "Kreditkonto 5 %";  // Negative balance
        }
    }
}
