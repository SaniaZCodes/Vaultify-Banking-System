import java.util.*;
public class CreateAccount 
{
    public static void create(ArrayList<Account> accountList, Scanner sc)
    {
        System.out.println();
        System.out.print("Enter account number: ");
        String accountNumber = sc.nextLine();

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter balance: Rs.");
        double balance = sc.nextDouble();

        sc.nextLine(); 

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        System.out.println("Which type of account you want to create?");
        System.out.println("1. Saving Account");
        System.out.println("2. Current Account");
        System.out.print("Enter your choice: ");
        int choice = sc.nextInt();
        
        sc.nextLine(); 

        Account newAccount = null;

        if(choice == 1)
        {
            newAccount = new SavingAccount(accountNumber, name, pin, balance);
        }
        else if(choice == 2)
        {
            newAccount = new CurrentAccount(accountNumber, name, pin, balance);
        }
        else
        {
            System.out.println("Invalid choice.");
            return;
        }

        accountList.add(newAccount);
        System.out.println("\nAccount created successfully!");
    }
}