package mdatah4;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Customer {
    private String name;
    private String surname;
    private String personalNumber;
    private List<Account> accounts;

    // Constructor to initialize a customer with their details
    public Customer(String name, String surname, String personalNumber) {
        this.name = name;
        this.surname = surname;
        this.personalNumber = personalNumber;
        this.accounts = new ArrayList<>();
    }

    // Change customer's name or surname based on non-empty input
    public void changeName(String name, String surname) {
        if (!name.isEmpty()) this.name = name;
        if (!surname.isEmpty()) this.surname = surname;
    }

    // Get the full info of a customer (personalNumber, name, surname, and accounts)
    public List<String> getInfo() {
        List<String> info = new ArrayList<>();
        info.add(personalNumber + " " + getFullName());
        for (Account account : accounts) {
            info.add(account.getAccountNumber() + " " + formatCurrency(account.getBalance()) + " " + account.getAccountType());
        }
        return info;
    }

    // Add an account to the customer's list of accounts
    public void addAccount(Account account) {
        accounts.add(account);
    }

    // Get specific account information by accountId
    public String getAccountInfo(int accountId) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountId) {
                return account.getAccountNumber() + " " + formatCurrency(account.getBalance()) + " " + account.getAccountType();
            }
        }
        return null;
    }

    // Make a deposit into the account with the given accountId
    public boolean deposit(int accountId, BigDecimal amount) {
        Account account = getAccountById(accountId);
        if (account != null) {
            return account.deposit(amount);
        }
        return false;
    }

    // Withdraw an amount from the account with the given accountId
    public boolean withdraw(int accountId, BigDecimal amount) {
        Account account = getAccountById(accountId);
        if (account != null) {
            return account.withdraw(amount);
        }
        return false;
    }

    // Get a list of transactions for the account with the given accountId
    public List<String> getTransactions(int accountId) {
        Account account = getAccountById(accountId);
        if (account != null) {
            return account.getTransactions();
        }
        return null;
    }

    // Close the account with the given accountId and return closure information
    public String closeAccount(int accountId) {
        Account account = getAccountById(accountId);
        if (account != null) {
            BigDecimal finalBalance = account.closeAccount();
            accounts.remove(account);
            return account.getAccountNumber() + " " + formatCurrency(finalBalance) + " " + account.getAccountType();
        }
        return null;
    }

    // Get all the accounts associated with the customer
    public List<String> getAllAccounts() {
        List<String> result = new ArrayList<>();
        for (Account account : accounts) {
            result.add(account.getAccountNumber() + " " + formatCurrency(account.getBalance()) + " " + account.getAccountType());
        }
        result.add(0, personalNumber + " " + getFullName());
        return result;
    }

    // Helper method to format currency in Swedish locale
    private String formatCurrency(BigDecimal value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.forLanguageTag("sv-SE"));
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator(' ');
        DecimalFormat df = new DecimalFormat("#,##0.00", symbols);
        return df.format(value) + " kr";
    }

    // Return the full name of the customer
    public String getFullName() {
        return name + " " + surname;
    }

    // Return the personal number of the customer
    public String getPersonalNumber() {
        return personalNumber;
    }

    // Find an account by its account number
    public Account getAccountById(int accountNumber) {
        return accounts.stream()
                .filter(a -> a.getAccountNumber() == accountNumber)
                .findFirst()
                .orElse(null);
    }
    public List<Account> getAccounts() {
        return accounts;  // Return list of all accounts
    }
}
