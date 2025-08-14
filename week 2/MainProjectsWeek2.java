import java.util.Scanner;

class MainProjectsWeek2 {
    // 2D array : [][0]=username, [][1]=password, [][2]=balance,[][3]=email
    static String[][] userDetails = new String[100][4];
    static int userCount = 0;

    // Register a new user
    public static void registerUser(String uname, String pass, double balance, String email) {

        userDetails[userCount][0] = uname;
        userDetails[userCount][1] = pass;
        userDetails[userCount][2] = String.valueOf(balance);
        userDetails[userCount][3] = email;

        userCount++;
        System.out.println("User registered successfully!");
    }

    // validate email id
    public static boolean validateEmail(String email) {
        // Simple regex for email validation
        String emailRegex = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$";
        return email.matches(emailRegex);
    }

    // Validate password
    public static boolean validatePassword(String pass) {
        // Use >= for length check, and OR (||) so any missing requirement fails
        // validation
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
            if (userDetails[i][0].equals(uname) && userDetails[i][1].equals(pass)) {
                System.out.println("Login successful.");
                detailforlogin(i);
                return i; // return index of logged-in user
            }
        }
        System.out.println("Invalid credentials.");
        return -1;
    }

    // Deposit money
    public static void depositMoney(double amount, int userIndex) {
        if (amount > 0) {
            double balance = Double.parseDouble(userDetails[userIndex][2]);
            balance += amount;
            userDetails[userIndex][2] = String.valueOf(balance);
            System.out.println("\nDeposit successful! New Balance: " + balance);
        } else {
            System.out.println("\nInvalid amount! Must be greater than 0.");
        }
    }

    // Withdraw money
    public static void withdrawMoney(double amount, int userIndex) {
        double balance = Double.parseDouble(userDetails[userIndex][2]);
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            userDetails[userIndex][2] = String.valueOf(balance);
            System.out.println("\nWithdrawal successful! New Balance: " + balance);
        } else {
            System.out.println("\nInvalid amount funds!");
        }
    }

    // Show balance
    public static void showBalance(int userIndex) {
        System.out.println("\nCurrent Balance: " + userDetails[userIndex][2]);
    }

    // View account details
    public static void viewAccountDetails(int userIndex) {
        System.out.println("\nUsername: " + userDetails[userIndex][0]);
        System.out.println("Email: " + userDetails[userIndex][3]);
        System.out.println("Balance: " + userDetails[userIndex][2]);
    }

    // Function to display details and options
    // by using chatgpt and only for looking good

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

    // for login successfully

    public static void detailforlogin(int userIndex) {
        Scanner sc = new Scanner(System.in);
        int choice;
        double amount;
        do {
            showFunctionMenuBox();
            /*
             * System.out.println("\n|----------------|");
             * System.out.println("|--- function ---|");
             * System.out.println("|----------------|\n");
             * System.out.println("1. Deposit Money");
             * System.out.println("2. Withdraw Money");
             * System.out.println("3. Show Balance");
             * System.out.println("4. View Account Details");
             * System.out.println("5. Log out");
             */
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

    /*
     * static void showPasswordRulesBox() {
     * String[] lines = {
     * "Password rules:",
     * "• At least 8 characters",
     * "• At least one uppercase letter",
     * "• At least one lowercase letter",
     * "• At least one digit",
     * "• At least one special character (_@#)"
     * };
     * 
     * // compute width
     * int w = 0;
     * for (String s : lines)
     * w = Math.max(w, s.length());
     * String border = "+" + "-".repeat(w + 2) + "+";
     * 
     * System.out.println(border);
     * for (String s : lines) {
     * System.out.println("| " + s + " ".repeat(w - s.length()) + " |");
     * }
     * System.out.println(border);
     * }
     */

    // by using chatgpt and only for looking good

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

    /*
     * static void showPasswordRulesBoxColor() {
     * final String RESET = "\u001B[0m";
     * final String CYAN = "\u001B[36m";
     * final String YELL = "\u001B[33m";
     * 
     * String[] lines = {
     * "Password rules:",
     * "• At least 8 characters",
     * "• At least one uppercase letter",
     * "• At least one lowercase letter",
     * "• At least one digit",
     * "• At least one special character (_@#)"
     * };
     * 
     * int w = 0;
     * for (String s : lines)
     * w = Math.max(w, s.length());
     * String border = "+" + "-".repeat(w + 2) + "+";
     * 
     * System.out.println(CYAN + border + RESET);
     * System.out.println(CYAN + "| " + YELL + lines[0] + " ".repeat(w -
     * lines[0].length()) + CYAN + " |" + RESET);
     * for (int i = 1; i < lines.length; i++) {
     * System.out
     * .println(CYAN + "| " + RESET + lines[i] + " ".repeat(w - lines[i].length()) +
     * CYAN + " |" + RESET);
     * }
     * System.out.println(CYAN + border + RESET);
     * }
     */

    // by using chatgpt and only for looking good

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        int loggedInUser = -1;
        String uname, pass;
        double balance;
        String email;
        do {
            showBankMenuBox();
            // System.out.println("\n|-----------------|");
            /*
             * System.out.println("|-----------------|");
             * System.out.println("|--- Bank Menu ---|");
             * System.out.println("|-----------------|\n");
             * System.out.println("1. Register User");
             * System.out.println("2. Login User");
             * System.out.println("3. Exit");
             * System.out.print("\nEnter choice: ");
             */
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new username minimum 8 characters long: ");
                    uname = sc.nextLine().toLowerCase();
                    if (uname.length() < 8) {
                        System.out.println("Username must be at least 8 characters long.");
                        return;
                    }
                    // Check for duplicate username
                    for (int i = 0; i < userCount; i++) {
                        if (userDetails[i][0].equals(uname)) {
                            System.out.println("Username already exists. Please try again.");
                            return;
                        }
                    }
                    // showPasswordRulesBoxColor();
                    System.out.println("");

                    showPasswordRulesBoxFancy();
                    System.out.println("");
                    // showPasswordRulesBox();
                    System.out.print("\nEnter password: ");
                    pass = sc.nextLine();
                    if (!validatePassword(pass)) {
                        return; // Exit if password validation fails
                    }
                    System.out.print("Enter Email id: ");
                    email = sc.nextLine().toLowerCase();
                    if (!validateEmail(email)) {
                        System.out.println("Invalid email format. Please try again.");
                        return; // Exit if email validation fails
                    }
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
                    loggedInUser = loginUser(uname, pass);
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