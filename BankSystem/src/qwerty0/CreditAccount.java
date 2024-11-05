package qwerty0;

import java.math.BigDecimal;

public class CreditAccount extends Account {
    private static final BigDecimal CREDIT_LIMIT = new BigDecimal("5000");
    private static final BigDecimal POSITIVE_INTEREST_RATE = new BigDecimal("0.011");
    private static final BigDecimal DEBT_INTEREST_RATE = new BigDecimal("0.05");

    public CreditAccount(int accountNumber) {
        super(accountNumber);
    }

    @Override
    public boolean withdraw(BigDecimal amount) {
        BigDecimal potentialBalance = balance.subtract(amount);
        if (amount.compareTo(BigDecimal.ZERO) <= 0 || potentialBalance.compareTo(CREDIT_LIMIT.negate()) < 0) {
            return false; // Cannot withdraw more than the credit limit
        }

        balance = potentialBalance;
        recordTransaction("-", amount);
        return true;
    }

    @Override
    public String closeAccount() {
        BigDecimal interest;
        if (balance.compareTo(BigDecimal.ZERO) >= 0) {
            interest = balance.multiply(POSITIVE_INTEREST_RATE);
        } else {
            interest = balance.multiply(DEBT_INTEREST_RATE).abs();
        }
        return String.format("%d %.2f kr Kreditkonto %.2f kr", accountNumber, balance, interest);
    }
}
