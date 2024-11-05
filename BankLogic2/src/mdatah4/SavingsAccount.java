package mdatah4;

import java.math.BigDecimal;

public class SavingsAccount extends Account {
    private static final BigDecimal WITHDRAWAL_FEE_PERCENTAGE = new BigDecimal("0.02");  // 2% withdrawal fee
    private static final BigDecimal CLOSING_INTEREST_RATE = new BigDecimal("0.024");  // 2.4% closing interest
    private boolean freeWithdrawalUsed = false;  // Track if free withdrawal has been used

    public SavingsAccount(int accountNumber) {
        super(accountNumber);
    }

    @Override
    public boolean withdraw(BigDecimal amount) {
        if (!freeWithdrawalUsed) {
            freeWithdrawalUsed = true;
            return super.withdraw(amount);  // First withdrawal is free
        } else {
            BigDecimal fee = amount.multiply(WITHDRAWAL_FEE_PERCENTAGE);
            if (balance.compareTo(amount.add(fee)) >= 0) {
                return super.withdraw(amount.add(fee));  // Apply fee for additional withdrawals
            }
        }
        return false;
    }

    @Override
    public BigDecimal closeAccount() {
        // Apply closing interest rate
        BigDecimal interest = balance.multiply(CLOSING_INTEREST_RATE);
        balance = balance.add(interest);
        BigDecimal finalBalance = balance;
        balance = BigDecimal.ZERO;  // Reset balance to 0 after closure
        return finalBalance;
    }

    @Override
    public String getAccountType() {
        return "Sparkonto 2,4 %";  // Fixed account type with 2.4% interest
    }
}
