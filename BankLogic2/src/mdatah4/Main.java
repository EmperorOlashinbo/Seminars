package mdatah4;

import java.util.Scanner;
import java.util.List;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static BankLogic bankLogic = new BankLogic();

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    createCustomer();
                    break;
                case 2:
                    deleteCustomer();
                    break;
                case 3:
                    createSavingsAccount();
                    break;
                case 4:
                    createCreditAccount();
                    break;
                case 5:
                    viewCustomer();
                    break;
                case 6:
                    depositMoney();
                    break;
                case 7:
                    withdrawMoney();
                    break;
                case 8:
                    viewTransactions();
                    break;
                case 9:
                    closeAccount();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nBank System Menu:");
        System.out.println("1. Create Customer");
        System.out.println("2. Delete Customer");
        System.out.println("3. Create Savings Account");
        System.out.println("4. Create Credit Account");
        System.out.println("5. View Customer Info");
        System.out.println("6. Deposit Money");
        System.out.println("7. Withdraw Money");
        System.out.println("8. View Transactions");
        System.out.println("9. Close Account");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void createCustomer() {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter personal number: ");
        String personalNumber = scanner.nextLine();

        if (bankLogic.createCustomer(firstName, lastName, personalNumber)) {
            System.out.println("Customer created successfully.");
        } else {
            System.out.println("Customer already exists.");
        }
    }

    private static void deleteCustomer() {
        System.out.print("Enter personal number of the customer to delete: ");
        String personalNumber = scanner.nextLine();
        List<String> customerInfo = bankLogic.deleteCustomer(personalNumber);

        if (customerInfo != null) {
            System.out.println("Customer deleted: " + customerInfo);
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void createSavingsAccount() {
        System.out.print("Enter personal number: ");
        String personalNumber = scanner.nextLine();
        int accountId = bankLogic.createSavingsAccount(personalNumber);

        if (accountId != -1) {
            System.out.println("Savings account created with account ID: " + accountId);
        } else {
            System.out.println("Unable to create savings account. Customer may not exist.");
        }
    }

    private static void createCreditAccount() {
        System.out.print("Enter personal number: ");
        String personalNumber = scanner.nextLine();
        int accountId = bankLogic.createCreditAccount(personalNumber);

        if (accountId != -1) {
            System.out.println("Credit account created with account ID: " + accountId);
        } else {
            System.out.println("Unable to create credit account. Customer may not exist.");
        }
    }

    private static void viewCustomer() {
        System.out.print("Enter personal number: ");
        String personalNumber = scanner.nextLine();
        List<String> customerInfo = bankLogic.getCustomer(personalNumber);

        if (customerInfo != null) {
            System.out.println("Customer Information:");
            for (String info : customerInfo) {
                System.out.println(info);
            }
        } else {
            System.out.println("Customer not found.");
        }
    }

    private static void depositMoney() {
        System.out.print("Enter personal number: ");
        String personalNumber = scanner.nextLine();
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();

        if (bankLogic.deposit(personalNumber, accountId, amount)) {
            System.out.println("Money deposited successfully.");
        } else {
            System.out.println("Deposit failed. Check customer and account information.");
        }
    }

    private static void withdrawMoney() {
        System.out.print("Enter personal number: ");
        String personalNumber = scanner.nextLine();
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();

        if (bankLogic.withdraw(personalNumber, accountId, amount)) {
            System.out.println("Money withdrawn successfully.");
        } else {
            System.out.println("Withdrawal failed. Check customer balance and account information.");
        }
    }

    private static void viewTransactions() {
        System.out.print("Enter personal number: ");
        String personalNumber = scanner.nextLine();
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();
        List<String> transactions = bankLogic.getTransactions(personalNumber, accountId);

        if (transactions != null && !transactions.isEmpty()) {
            System.out.println("Transactions:");
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        } else if (transactions != null && transactions.isEmpty()) {
            System.out.println("No transactions found.");
        } else {
            System.out.println("Customer or account not found.");
        }
    }

    private static void closeAccount() {
        System.out.print("Enter personal number: ");
        String personalNumber = scanner.nextLine();
        System.out.print("Enter account ID: ");
        int accountId = scanner.nextInt();

        String accountInfo = bankLogic.closeAccount(personalNumber, accountId);
        if (accountInfo != null) {
            System.out.println("Account closed. Final balance: " + accountInfo);
        } else {
            System.out.println("Account or customer not found.");
        }
    }
}