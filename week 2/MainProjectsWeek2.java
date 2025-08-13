import java.util.Scanner;

class MainProjectsWeek2 {
    // 2D array : [][0]=username, [][1]=password, [][2]=balance
    static String[][] userDetails = new String[100][3];
    static int userCount = 0;

    // Register a new user
    public static void registerUser(String uname,String pass,double balance) {
        
        userDetails[userCount][0] = uname;
        userDetails[userCount][1] = pass;
        userDetails[userCount][2] = String.valueOf(balance);

        userCount++;
        System.out.println("User registered successfully!");
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
        System.out.println("Balance: " + userDetails[userIndex][2]);
    }

    //for login successfully
    public static void detailforlogin(int userIndex){
            Scanner sc = new Scanner(System.in);
            int choice;
            double amount;
            do{
            System.out.println("\n|----------------|");
            System.out.println("|--- function ---|");
            System.out.println("|----------------|\n");
            System.out.println("1. Deposit Money");
            System.out.println("2. Withdraw Money");
            System.out.println("3. Show Balance");
            System.out.println("4. View Account Details");
            System.out.println("5. Log out");
            System.out.print("\nEnter choice: ");
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    amount = sc.nextDouble();
                    depositMoney(amount,userIndex);
                    break;
                case 2: 
                    System.out.print("Enter amount to withdraw: ");
                    amount = sc.nextDouble();
                    withdrawMoney(amount,userIndex);
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
        }while (choice != 5);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        int loggedInUser = -1;
        String uname,pass;
        double balance;

        do {
            System.out.println("|-----------------|");
            System.out.println("|--- Bank Menu ---|");
            System.out.println("|-----------------|\n");
            System.out.println("1. Register User");
            System.out.println("2. Login User");
            System.out.println("3. Exit");
            System.out.print("\nEnter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new username: ");
                    uname = sc.nextLine().toLowerCase();

                    // Check for duplicate username
                    for (int i = 0; i < userCount; i++) {
                        if (userDetails[i][0].equals(uname)) {
                            System.out.println("Username already exists. Please try again.");
                            return;
                        }
                    }

                    System.out.print("Enter password: ");
                    pass = sc.nextLine();

                    System.out.print("Enter starting balance: ");
                    balance = sc.nextDouble();
                    sc.nextLine();

                    registerUser(uname,pass,balance);
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
