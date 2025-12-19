import java.util.Scanner;

public class Deposit 
{
    public static void makeDeposit(Account acc, Scanner sc) 
    {
        System.out.print("Enter the amount to deposit: Rs.");
        double amount = 0;
        try 
        {
            amount = sc.nextDouble();
            sc.nextLine(); 
            acc.deposit(amount);
        } 
        catch (java.util.InputMismatchException e) 
        {
            System.out.println("Invalid amount input.");
            sc.nextLine(); 
        }
    }
}