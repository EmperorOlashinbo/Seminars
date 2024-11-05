package qwerty0;

import java.math.BigDecimal;

public class SavingsAccount extends Account {
    private int freeWithdrawals = 1;
    private static final BigDecimal WITHDRAWAL_FEE_PERCENTAGE = new BigDecimal("0.02");
    private static final BigDecimal INTEREST_RATE = new BigDecimal("0.024");

    public SavingsAccount(int accountNumber) {
        super(accountNumber);
    }

    @Override
    public boolean withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0 || balance.compareTo(amount) < 0) {
            return false; // Cannot withdraw negative amounts or more than balance
        }

        if (freeWithdrawals <= 0) {
            BigDecimal fee = amount.multiply(WITHDRAWAL_FEE_PERCENTAGE);
            amount = amount.add(fee); // Add fee if no free withdrawals left
            if (balance.compareTo(amount) < 0) {
                return false; // Not enough balance to cover withdrawal + fee
            }
        }

        freeWithdrawals--; // Decrease free withdrawals
        balance = balance.subtract(amount);
        recordTransaction("-", amount);
        return true;
    }

    @Override
    public String closeAccount() {
        BigDecimal interest = balance.multiply(INTEREST_RATE);
        return String.format("%d %.2f kr Sparkonto %.2f kr", accountNumber, balance, interest);
    }
}
