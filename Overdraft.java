import java.util.Scanner;
public class Overdraft 
{
    public static void makeOverdraft(CurrentAccount ca, Scanner sc)
    {
        System.out.print("Enter the amount to withdraw: Rs.");
        double amount= sc.nextDouble();
        sc.nextLine();

        ca.overDraftWithdraw(amount);
    }
}