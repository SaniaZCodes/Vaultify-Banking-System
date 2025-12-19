import java.io.Serializable;
import java.util.Scanner;

public class SavingAccount extends Account implements Serializable 
{
    private static final long serialVersionUID = 1L;
    private final double annualRate = 4.0;
    private final double monthlyRate = annualRate / 12;
    // Note: transient Scanner is used because Scanner objects are not Serializable
    transient Scanner sc = new Scanner(System.in); 

    public SavingAccount(String accountNumber, String name, String pin, double balance) 
    {
        super(accountNumber, name, pin, "Savings", balance);
    }

    public void calculateInterest() 
    {
        System.out.println("1. Annual Interest");
        System.out.println("2. Monthly Interest");
        System.out.println("3. Back");
        System.out.print("Enter your choice: ");
        
        int choice = 0;
        try 
        {
            choice = sc.nextInt();
            sc.nextLine(); 
        } 
        catch (java.util.InputMismatchException e) 
        {
            System.out.println("Invalid input. Returning to menu.");
            sc.nextLine(); 
            return;
        }
        
        switch (choice) 
        {
            case 1:
                double annualInterest = balance * (annualRate / 100);
                System.out.println("Annual Interest: Rs." + annualInterest);
                balance = balance + annualInterest;
                System.out.println("Updated Balance: Rs." + balance);
                history.add(new Transaction("Annual Interest Applied", annualInterest));
                break;

            case 2:
                double monthlyInterest = balance * (monthlyRate / 100);
                System.out.println("Monthly Interest: Rs." + monthlyInterest);
                balance = balance + monthlyInterest;
                System.out.println("Updated Balance: Rs." + balance);
                history.add(new Transaction("Monthly Interest Applied", monthlyInterest));
                break;

            case 3:
                return;
                
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }
}