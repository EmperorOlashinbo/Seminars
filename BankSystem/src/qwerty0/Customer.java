package qwerty0;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String surname;
    private String personalNumber;
    private List<Account> accounts;

    public Customer(String name, String surname, String personalNumber) {
        this.name = name;
        this.surname = surname;
        this.personalNumber = personalNumber;
        this.accounts = new ArrayList<>();
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public String getFullName() {
        return name + " " + surname;
    }

    public void changeName(String newName, String newSurname) {
        if (!newName.isEmpty()) {
            this.name = newName;
        }
        if (!newSurname.isEmpty()) {
            this.surname = newSurname;
        }
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccountById(int accountNumber) {
        return accounts.stream()
                .filter(a -> a.getAccountNumber() == accountNumber)
                .findFirst()
                .orElse(null);
    }
}
