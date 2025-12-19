import java.util.*;
public class Login 
{
    public static Account login(ArrayList<Account> accountList, Scanner sc) 
    {
        while (true) 
            {
                System.out.println();
                System.out.print("Enter your account number: ");
                String inputAccountNumber = sc.nextLine().trim();
                System.out.print("Enter PIN: ");
                String inputPin = sc.nextLine();

                for (Account i : accountList) 
                    {
                        if (inputAccountNumber.equals(i.getAccountNumber()) && inputPin.equals(i.getPin())) 
                            {
                                return i;
                            }
                    }
                System.out.println("Invalid account number or PIN. Try again.");
        }
    }
}