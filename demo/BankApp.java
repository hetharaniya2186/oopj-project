import java.util.Scanner;

//user class to hold user details
class User {

    // User attributes
    private String username;
    private String password;
    private String email;
    private double balance;

    // Constructor
    public User(String username, String password, String email, double balance) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.balance = balance;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// Main Bank Application
public class BankApp {
    static User[] users = new User[100];
    static int userCount = 0;

    // Register a new user
    public static void registerUser(String uname, String pass, double balance, String email) {
        users[userCount] = new User(uname, pass, email, balance);
        userCount++;
        System.out.println("User registered successfully!");
    }

    // Validate email
    public static boolean validateEmail(String email) {
        String emailRegex = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";
        return email.matches(emailRegex);
    }

    // Validate password
    public static boolean validatePassword(String pass) {
        if (pass.length() < 8 ||
                !pass.matches(".*[A-Z].*") ||
                !pass.matches(".*[a-z].*") ||
                !pass.matches(".*[0-9].*") ||
                !pass.matches(".*[_@#].*")) {
            showPasswordRulesBoxFancy();
            return false;
        }
        return true;
    }

    // Login user
    public static int loginUser(String uname, String pass) {
        for (int i = 0; i < userCount; i++) {
            if (users[i].getUsername().equals(uname) && users[i].getPassword().equals(pass)) {
                System.out.println("Login successful.");
                detailforlogin(i);
                return i;
            }
        }
        System.out.println("Invalid credentials.");
        return -1;
    }

    // Deposit money
    public static void depositMoney(double amount, int userIndex) {
        if (amount > 0) {
            users[userIndex].deposit(amount);
            System.out.println("\nDeposit successful! New Balance: " + users[userIndex].getBalance());
        } else {
            System.out.println("\nInvalid amount! Must be greater than 0.");
        }
    }

    // Withdraw money
    public static void withdrawMoney(double amount, int userIndex) {
        if (users[userIndex].withdraw(amount)) {
            System.out.println("\nWithdrawal successful! New Balance: " + users[userIndex].getBalance());
        } else {
            System.out.println("\nInvalid amount or insufficient funds!");
        }
    }

    // Show balance
    public static void showBalance(int userIndex) {
        System.out.println("\nCurrent Balance: " + users[userIndex].getBalance());
    }

    // View account details
    public static void viewAccountDetails(int userIndex) {
        System.out.println("\nUsername: " + users[userIndex].getUsername());
        System.out.println("Email: " + users[userIndex].getEmail());
        System.out.println("Balance: " + users[userIndex].getBalance());
    }

    // Menu for logged-in user
    public static void detailforlogin(int userIndex) {
        Scanner sc = new Scanner(System.in);
        int choice;
        double amount;
        do {
            showFunctionMenuBox();
            System.out.print("\nEnter choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    amount = sc.nextDouble();
                    depositMoney(amount, userIndex);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    amount = sc.nextDouble();
                    withdrawMoney(amount, userIndex);
                    break;
                case 3:
                    showBalance(userIndex);
                    break;
                case 4:
                    viewAccountDetails(userIndex);
                    break;
                case 5:
                    System.out.println("Log out successfully...\n");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 5);
    }

    // Show Password Rules Box "chatgpt"
    static void showPasswordRulesBoxFancy() {
        final String RESET = "\u001B[0m";
        final String CYAN = "\u001B[36m";
        final String YELLOW = "\u001B[33m";

        String[] lines = {
                "Password rules:",
                "--> At least 8 characters",
                "--> At least one uppercase letter",
                "--> At least one lowercase letter",
                "--> At least one digit",
                "--> At least one special character (_@#)"
        };

        int w = 0;
        for (String s : lines)
            w = Math.max(w, s.length());
        String top = CYAN + "┌" + "─".repeat(w + 2) + "┐" + RESET;
        String bottom = CYAN + "└" + "─".repeat(w + 2) + "┘" + RESET;

        System.out.println(top);
        for (int i = 0; i < lines.length; i++) {
            if (i == 0) {
                System.out.println(
                        CYAN + "│ " + YELLOW + lines[i] + " ".repeat(w - lines[i].length()) + CYAN + " │" + RESET);
            } else {
                System.out.println(
                        CYAN + "│ " + RESET + lines[i] + " ".repeat(w - lines[i].length()) + CYAN + " │" + RESET);
            }
        }
        System.out.println(bottom);
    }

    // Show main bank menu "chatgpt"
    static void showBankMenuBox() {
        final String RESET = "\u001B[0m";
        final String CYAN = "\u001B[36m";
        final String YELLOW = "\u001B[33m";

        String[] lines = {
                "Bank Menu",
                "1. Register User",
                "2. Login User",
                "3. Exit"
        };

        int w = 0;
        for (String s : lines)
            w = Math.max(w, s.length());
        String top = CYAN + "┌" + "─".repeat(w + 2) + "┐" + RESET;
        String bottom = CYAN + "└" + "─".repeat(w + 2) + "┘" + RESET;

        System.out.println(top);
        for (int i = 0; i < lines.length; i++) {
            if (i == 0) {
                System.out.println(
                        CYAN + "│ " + YELLOW + lines[i] + " ".repeat(w - lines[i].length()) + CYAN + " │" + RESET);
            } else {
                System.out.println(
                        CYAN + "│ " + RESET + lines[i] + " ".repeat(w - lines[i].length()) + CYAN + " │" + RESET);
            }
        }
        System.out.println(bottom);
        System.out.print("\nEnter choice: ");
    }

    // Show function menu "chatgpt"
    static void showFunctionMenuBox() {
        final String RESET = "\u001B[0m";
        final String CYAN = "\u001B[36m";
        final String YELLOW = "\u001B[33m";

        String[] lines = {
                "Function Menu",
                "1. Deposit Money",
                "2. Withdraw Money",
                "3. Show Balance",
                "4. View Account Details",
                "5. Log out"
        };

        int w = 0;
        for (String s : lines)
            w = Math.max(w, s.length());
        String top = CYAN + "┌" + "─".repeat(w + 2) + "┐" + RESET;
        String bottom = CYAN + "└" + "─".repeat(w + 2) + "┘" + RESET;

        System.out.println("\n" + top);
        for (int i = 0; i < lines.length; i++) {
            if (i == 0) {
                System.out.println(
                        CYAN + "│ " + YELLOW + lines[i] + " ".repeat(w - lines[i].length()) + CYAN + " │" + RESET);
            } else {
                System.out.println(
                        CYAN + "│ " + RESET + lines[i] + " ".repeat(w - lines[i].length()) + CYAN + " │" + RESET);
            }
        }
        System.out.println(bottom);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        String uname, pass, email;
        double balance;

        do {
            showBankMenuBox();
            choice = sc.nextInt();
            sc.nextLine();

            // User choices
            switch (choice) {
                case 1:
                    System.out.print("Enter new username minimum 3 characters long: ");
                    uname = sc.nextLine().toLowerCase();
                    if (uname.length() < 3) {
                        System.out.println("Username must be at least 3 characters long.");
                        break;
                    }
                    for (int i = 0; i < userCount; i++) {
                        if (users[i].getUsername().equals(uname)) {
                            System.out.println("Username already exists. Please try again.");
                            break;
                        }
                    }

                    showPasswordRulesBoxFancy();
                    do {
                        System.out.print("\nEnter password: ");
                        pass = sc.nextLine();
                    } while (!validatePassword(pass));

                    do {
                        System.out.print("Enter Email id: ");
                        email = sc.nextLine().toLowerCase();
                        if (!validateEmail(email)) {
                            System.out.println("Invalid email format. Please try again.");
                        }
                    } while (!validateEmail(email));

                    System.out.print("Enter starting balance: ");
                    balance = sc.nextDouble();
                    sc.nextLine();

                    registerUser(uname, pass, balance, email);
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    uname = sc.nextLine().toLowerCase();
                    System.out.print("Enter password: ");
                    pass = sc.nextLine();
                    loginUser(uname, pass);
                    break;

                case 3:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 3);
    }
}