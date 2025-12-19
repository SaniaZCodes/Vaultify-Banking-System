import java.util.ArrayList;
import java.util.Scanner;

public class CurrentAccountDashboard 
{
    public static void show(CurrentAccount ca, Scanner sc, ArrayList<Account> accountList) 
    {
        while (true) 
            {
                System.out.println("\n--- Current Account Dashboard ---");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Overdraft Withdraw");
                System.out.println("4. Check Balance");
                System.out.println("5. Account Details");
                System.out.println("6. Transaction History");
                System.out.println("7. Transfer Money");
                System.out.println("8. Logout");
                System.out.print("Enter your choice: ");
            
                int choice = 0;
                try 
                {
                    choice = sc.nextInt();
                    sc.nextLine(); 
                } 
                catch (java.util.InputMismatchException e) 
                {
                    System.out.println("Invalid choice input. Please enter a number.");
                    sc.nextLine(); 
                    continue;
                }

                switch (choice) 
                {
                case 1:
                    System.out.print("Enter deposit amount: Rs.");
                    double depositAmount = sc.nextDouble();
                    ca.deposit(depositAmount);
                    Persistence.saveAccounts(accountList);
                    break;

                case 2:
                    System.out.print("Enter withdraw amount: Rs.");
                    double withdrawAmount = sc.nextDouble();
                    ca.withdraw(withdrawAmount);
                    Persistence.saveAccounts(accountList);
                    break;

                case 3:
                    System.out.print("Enter overdraft withdraw amount: Rs.");
                    double overdraftAmount = sc.nextDouble();
                    ca.overDraftWithdraw(overdraftAmount);
                    Persistence.saveAccounts(accountList);
                    break;
                    
                case 4:
                    System.out.println("Balance: Rs." + ca.getBalance());
                    break;

                case 5:
                    System.out.println("\nAccount Details:");
                    ca.viewDetails();
                    break;

                case 6:
                    System.out.println("\nTransaction History:");
                    ca.showHistory();
                    break;

                case 7:
                    Transfer.makeTransfer(ca, accountList, sc);
                    Persistence.saveAccounts(accountList);
                    break;

                case 8:
                    System.out.println("Logging out...");
                    return;
                    
                default:
                    System.out.println("Invalid choice. Try again.");
                }
            }
    }
}