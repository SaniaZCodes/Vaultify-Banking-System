import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable 
{
    private static final long serialVersionUID = 1L;
    private String accountNumber;
    private String name;
    private String pin;
    private String accountType;
    protected double balance;
    protected ArrayList<Transaction> history;

    public Account(String accountNumber, String name, String pin, String accountType, double balance) 
    {
        this.accountNumber = accountNumber;
        this.name = name;
        this.pin = pin;
        this.accountType = accountType;
        this.balance = balance;
        history = new ArrayList<>();
    }

    public void deposit(double amount) 
    {
        if (amount > 0) 
            {
                balance = balance + amount;
                System.out.println("Rs." + amount + " is successfully deposited.");
                System.out.println("Updated Balance: Rs." + balance);
                history.add(new Transaction("Deposit", amount));
            } 
        else 
            {
                System.out.println("Invalid amount.");
            }
    }
        
    public void withdraw(double amount) 
    {
        if (amount <= balance && amount > 0) 
            {
                balance = balance - amount;
                System.out.println("Rs." + amount + " is successfully withdrawn.");
                System.out.println("Updated Balance: Rs." + balance);
                history.add(new Transaction("Withdraw", amount));
            } 
        else if (amount > balance) 
            {
                System.out.println("Insufficient balance.");
            } 
        else if (amount <= 0) 
            {
                System.out.println("Invalid amount.");
            }
    }
    
    public double getBalance() 
    {
        return balance;
    }

    public void viewDetails() 
    {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Name: " + name);
        System.out.println("Account Type: " + accountType);
        System.out.println("Balance: Rs." + balance);
    }

    public String getAccountNumber() 
    {
        return accountNumber;
    }

    public String getName() 
    {
        return name;
    }

    public String getPin() 
    {
        return pin;
    }

    public ArrayList<Transaction> getHistory() 
    {
        return history;
    }

    public void showHistory() 
    {
        if (history.isEmpty()) 
            {
                System.out.println("No transactions yet.");
                return;
            }
        for (Transaction t : history) 
            {
                System.out.println(t.getDate() + " | " + t.getType() + " | " + t.getAmount());
            }
    }
}