import java.util.*;
public class Transfer 
{
    public static void makeTransfer(Account sender, ArrayList<Account> accountList, Scanner sc) 
    {
        System.out.print("Enter receiver's account number: ");
        String receiverAccountNumber = sc.nextLine().trim();

        Account receiver = null;
        for (Account i : accountList) 
            {
                if (i.getAccountNumber().equals(receiverAccountNumber)) 
                    {
                        receiver = i;
                        break;
                    }
            }

        if (receiver == null) 
            {
                System.out.println("Receiver account not found.");
                return;
            }
            // can not be same receiver and sender
        if (receiver.getAccountNumber().equals(sender.getAccountNumber())) 
            {
                System.out.println("Cannot transfer money to your own account. Use deposit instead.");
                return;
            }

        System.out.print("Enter transfer amount: Rs.");
        double amount = 0;

        try 
        {
            amount = sc.nextDouble();
            sc.nextLine(); 
        } 
        catch (java.util.InputMismatchException e) 
        {
            System.out.println("Invalid amount input.");
            sc.nextLine(); // Clear the invalid input
            return;
        }

        if (amount <= 0) 
            {
                System.out.println("Invalid amount.");
                return;
            }

        if (sender.getBalance() >= amount) 
            {            
                if (sender.getBalance() >= amount) 
                    {
                        sender.balance -= amount;
                        receiver.balance += amount;
                
                        System.out.println("\nTransfer successful!");
                        System.out.println("Sender's Updated Balance: Rs." + sender.getBalance());
                        System.out.println("Receiver's Updated Balance: Rs." + receiver.getBalance());
                
                        sender.history.add(new Transaction("Transfer to " + receiver.getName() + " (" + receiver.getAccountNumber() + ")", amount));
                        receiver.history.add(new Transaction("Transfer from " + sender.getName() + " (" + sender.getAccountNumber() + ")", amount));
                    } 
                else 
                    {
                        System.out.println("Insufficient balance for transfer.");
                    }
            } 
        else 
            {
                System.out.println("Insufficient balance for transfer.");
            }
    }
}