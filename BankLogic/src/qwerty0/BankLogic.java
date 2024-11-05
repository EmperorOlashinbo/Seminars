package qwerty0;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class BankLogic {
    private List<Customer> customers = new ArrayList<>();
    private int nextAccountNumber = 1001;

    // Create a new customer
    public boolean createCustomer(String name, String surname, String personalNumber) {
        if (getCustomerObject(personalNumber) == null) {
            customers.add(new Customer(name, surname, personalNumber));
            return true;
        }
        return false;
    }

    // Get all customers in the system
    public List<String> getAllCustomers() {
        List<String> customersList = new ArrayList<>();
        for (Customer customer : customers) {
            customersList.add(customer.getPersonalNumber() + " " + customer.getFullName());
        }
        return customersList;
    }

    // Change the name of an existing customer
    public boolean changeCustomerName(String name, String surname, String personalNumber) {
        Customer customer = getCustomerObject(personalNumber);
        if (customer != null) {
            customer.changeName(name, surname);
            return true;
        }
        return false;
    }

    // Get details of a specific customer, including accounts
    public List<String> getCustomer(String personalNumber) {
        Customer customer = getCustomerObject(personalNumber);
        if (customer != null) {
            List<String> customerInfo = new ArrayList<>();
            customerInfo.add(customer.getPersonalNumber() + " " + customer.getFullName());
            for (Account account : customer.getAccounts()) {
                customerInfo.add(account.toString()); // Ensure toString() in Account is properly formatted
            }
            return customerInfo;
        }
        return null;
    }

    // Helper method to retrieve customer by personal number
    private Customer getCustomerObject(String personalNumber) {
        return customers.stream()
                .filter(c -> c.getPersonalNumber().equals(personalNumber))
                .findFirst()
                .orElse(null);
    }

    // Create a savings account for a customer
    public int createSavingsAccount(String personalNumber) {
        Customer customer = getCustomerObject(personalNumber);
        if (customer != null) {
            SavingsAccount account = new SavingsAccount(nextAccountNumber++);
            customer.addAccount(account);
            return account.getAccountNumber();
        }
        return -1;
    }

    // Create a credit account for a customer
    public int createCreditAccount(String personalNumber) {
        Customer customer = getCustomerObject(personalNumber);
        if (customer != null) {
            CreditAccount account = new CreditAccount(nextAccountNumber++);
            customer.addAccount(account);
            return account.getAccountNumber();
        }
        return -1;
    }

    // Deposit into an account
    public boolean deposit(String personalNumber, int accountNumber, int amount) {
        Customer customer = getCustomerObject(personalNumber);
        if (customer != null) {
            Account account = customer.getAccountById(accountNumber);
            if (account != null) {
                return account.deposit(new BigDecimal(amount));
            }
        }
        return false;
    }

    // Withdraw from an account
    public boolean withdraw(String personalNumber, int accountNumber, int amount) {
        Customer customer = getCustomerObject(personalNumber);
        if (customer != null) {
            Account account = customer.getAccountById(accountNumber);
            if (account != null) {
                return account.withdraw(new BigDecimal(amount));
            }
        }
        return false;
    }

    // Get specific account details by personal number and account number
    public String getAccount(String personalNumber, int accountNumber) {
        Customer customer = getCustomerObject(personalNumber);
        if (customer != null) {
            Account account = customer.getAccountById(accountNumber);
            if (account != null) {
                return account.toString(); // Assuming the toString() method in Account is properly formatted
            }
        }
        return null; // If account not found
    }

    // Close an account and return information about the closure
    public String closeAccount(String personalNumber, int accountNumber) {
        Customer customer = getCustomerObject(personalNumber);
        if (customer != null) {
            Account account = customer.getAccountById(accountNumber);
            if (account != null) {
                String accountClosureInfo = account.closeAccount();
                customer.getAccounts().remove(account); // Remove the account after closing
                return accountClosureInfo;
            }
        }
        return null;
    }

    // Get all transactions for a specific account
    public List<String> getTransactions(String personalNumber, int accountId) {
        Customer customer = getCustomerObject(personalNumber);
        if (customer != null) {
            Account account = customer.getAccountById(accountId);
            if (account != null) {
                return account.getTransactions();
            }
        }
        return null;
    }

    // Delete a customer and return the information about them, including closed accounts
    public List<String> deleteCustomer(String personalNumber) {
        Customer customer = getCustomerObject(personalNumber);
        if (customer != null) {
            List<String> customerInfo = getCustomer(personalNumber); // Get all customer details
            customers.remove(customer); // Remove the customer from the system
            return customerInfo;
        }
        return null;
    }

    // Helper method to format currency as per Swedish locale
    public static String formatCurrency(BigDecimal value) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.forLanguageTag("sv-SE"));
        symbols.setDecimalSeparator(',');
        symbols.setGroupingSeparator(' ');
        DecimalFormat formatter = new DecimalFormat("#,##0.00", symbols);
        return formatter.format(value) + " kr";
    }
}
