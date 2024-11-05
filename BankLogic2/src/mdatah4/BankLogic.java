package mdatah4;

import java.math.BigDecimal;
import java.util.*;

public class BankLogic {
    private Map<String, Customer> customers;
    private int accountCounter = 1000;

    public BankLogic() {
        customers = new HashMap<>();
    }

    public boolean createCustomer(String name, String surname, String pNo) {
        if (customers.containsKey(pNo)) return false;
        customers.put(pNo, new Customer(name, surname, pNo));
        return true;
    }

    public boolean changeCustomerName(String name, String surname, String pNo) {
        Customer customer = customers.get(pNo);
        if (customer != null) {
            customer.changeName(name, surname);
            return true;
        }
        return false;
    }

    public List<String> getCustomer(String pNo) {
        Customer customer = customers.get(pNo);
        return customer != null ? customer.getInfo() : null;
    }

    public List<String> getAllCustomers() {
        List<String> customersList = new ArrayList<>();
        for (Customer customer : customers.values()) {
            customersList.add(customer.getPersonalNumber() + " " + customer.getFullName());
        }
        return customersList;
    }

    public int createSavingsAccount(String pNo) {
        Customer customer = customers.get(pNo);
        if (customer != null) {
            SavingsAccount account = new SavingsAccount(++accountCounter);
            customer.addAccount(account);
            return account.getAccountNumber();
        }
        return -1;
    }

    public int createCreditAccount(String pNo) {
        Customer customer = customers.get(pNo);
        if (customer != null) {
            CreditAccount account = new CreditAccount(++accountCounter);
            customer.addAccount(account);
            return account.getAccountNumber();
        }
        return -1;
    }

    public String getAccount(String pNo, int accountId) {
        Customer customer = customers.get(pNo);
        if (customer != null) {
            return customer.getAccountInfo(accountId);
        }
        return null;
    }

    public boolean deposit(String pNo, int accountNumber, double amount) {
        Customer customer = customers.get(pNo);
        if (customer != null) {
            Account account = customer.getAccountById(accountNumber);
            if (account != null) {
                return account.deposit(BigDecimal.valueOf(amount)); // Convert double to BigDecimal
            }
        }
        return false;
    }

    public boolean withdraw(String pNo, int accountNumber, double amount) {
        Customer customer = customers.get(pNo);
        if (customer != null) {
            Account account = customer.getAccountById(accountNumber);
            if (account != null) {
                return account.withdraw(BigDecimal.valueOf(amount)); // Convert double to BigDecimal
            }
        }
        return false;
    }

    public List<String> getTransactions(String pNo, int accountId) {
        Customer customer = customers.get(pNo);
        if (customer != null) {
            return customer.getTransactions(accountId);
        }
        return null;
    }

    public String closeAccount(String pNo, int accountId) {
        Customer customer = customers.get(pNo);
        if (customer != null) {
            return customer.closeAccount(accountId);
        }
        return null;
    }

    public List<String> deleteCustomer(String pNo) {
        Customer customer = customers.get(pNo);
        if (customer != null) {
            List<String> customerInfo = customer.getAllAccounts(); // Get all account information before deleting
            for (Account account : customer.getAccounts()) {
                account.closeAccount(); // Ensure accounts are closed before deletion
            }
            customers.remove(pNo); // Remove customer from the system
            return customerInfo;
        }
        return null;
    }
}
