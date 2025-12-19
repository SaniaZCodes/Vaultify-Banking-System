import java.util.Scanner;
public class Withdraw 
{   
    public static void makeWithdraw(Account acc, Scanner sc)
    {
        System.out.print("Enter amount to withdraw: Rs.");
        double amount= sc.nextDouble();
        sc.nextLine();
        acc.withdraw(amount);
    }
}