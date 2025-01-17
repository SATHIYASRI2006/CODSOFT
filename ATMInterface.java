import java.util.Scanner;

// Class to represent the user's bank account
class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance > 0 ? initialBalance : 0;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful! Current balance: " + balance);
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful! Current balance: " + balance);
        } else if (amount > balance) {
            System.out.println("Insufficient funds!");
        } else {
            System.out.println("Invalid withdrawal amount!");
        }
    }

    public void checkBalance() {
        System.out.println("Current balance: " + balance);
    }
}

// ATM Machine class
class ATM {
    private BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;

            System.out.println("Welcome to the ATM!");

            do {
                displayMenu();
                choice = getValidIntInput(scanner, "Enter your choice: ");

                switch (choice) {
                    case 1:
                        account.checkBalance();
                        break;
                    case 2:
                        handleDeposit(scanner);
                        break;
                    case 3:
                        handleWithdrawal(scanner);
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                }
            } while (choice != 4);
        }
    }

    private void displayMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Exit");
    }

    private void handleDeposit(Scanner scanner) {
        double depositAmount = getValidDoubleInput(scanner, "Enter the amount to deposit: ");
        account.deposit(depositAmount);
    }

    private void handleWithdrawal(Scanner scanner) {
        double withdrawAmount = getValidDoubleInput(scanner, "Enter the amount to withdraw: ");
        account.withdraw(withdrawAmount);
    }

    private int getValidIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                return scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    private double getValidDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextDouble()) {
                return scanner.nextDouble();
            } else {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.next(); // Clear invalid input
            }
        }
    }
}

// Main class
public class ATMInterface {
    public static void main(String[] args) {
        BankAccount userAccount = new BankAccount(1000.0); // Initial balance
        ATM atm = new ATM(userAccount);
        atm.start();
    }
}
