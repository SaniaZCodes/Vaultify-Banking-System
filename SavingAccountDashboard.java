import java.util.ArrayList;
import java.util.Scanner;

public class SavingAccountDashboard 
{
    public static void show(SavingAccount sa, Scanner sc, ArrayList<Account> accountList) 
    {
        while (true) 
            {
                System.out.println("\n--- Saving Account Dashboard ---");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Calculate Interest");
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
                        sa.deposit(depositAmount);
                        Persistence.saveAccounts(accountList);
                        break;
                
                    case 2:
                        System.out.print("Enter withdraw amount: Rs.");
                        double withdrawAmount = sc.nextDouble();
                        sa.withdraw(withdrawAmount);
                        Persistence.saveAccounts(accountList);
                        break;

                    case 3:
                        sa.calculateInterest();
                        Persistence.saveAccounts(accountList);
                        break;
                
                    case 4:
                        System.out.println("Balance: Rs." + sa.getBalance());
                        break;
                
                    case 5:
                        System.out.println("\nAccount Details:");
                        sa.viewDetails();
                        break;

                    case 6:
                        System.out.println("\nTransaction History:");
                        sa.showHistory();
                        break;

                    case 7:
                        Transfer.makeTransfer(sa, accountList, sc);
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