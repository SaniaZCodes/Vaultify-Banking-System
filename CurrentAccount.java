import java.io.Serializable;

public class CurrentAccount extends Account implements Serializable 
{
    private static final long serialVersionUID = 1L;
    private final int minimumBalance = 5000;
    private final int overdraftBalanceLimit = 9000;

    public CurrentAccount(String accountNumber, String name, String pin, double balance) 
    {
        super(accountNumber, name, pin, "Current", balance); 
    }

    // Withdraw with minimum balance check
    @Override
    public void withdraw(double amount) 
    {
        if (amount <= 0) 
            {
                System.out.println("Invalid withdraw amount");
                return;
            }
        if (amount > balance) 
            {
                System.out.println("Insufficient balance.");
                return;
            }

        double projectedBalance = balance - amount;
        if (projectedBalance >= minimumBalance) 
            {
                balance -= amount;
                System.out.println("Rs. " + amount + " withdrawn successfully.");
                System.out.println("Updated Balance: Rs. " + balance);
                history.add(new Transaction("Withdraw", amount));
            } 
        else 
            {
                System.out.println("Cannot withdraw amount. Projected balance (Rs." + projectedBalance + ") will be below minimum balance (Rs." + minimumBalance + ").");
            }
    }

    // Overdraft facility method
    public void overDraftWithdraw(double amount) 
    {
        if (amount <= 0) 
            {
                System.out.println("Invalid amount.");
                return;
            }

        if (balance - amount >= -overdraftBalanceLimit) 
            {
                balance -= amount;
                System.out.println("Rs." + amount + " withdrawn successfully.");
                System.out.println("Current Balance: Rs." + balance);
                history.add(new Transaction("Overdraft Withdraw", amount));

                if (balance < 0) 
                    {
                        System.out.println("Overdraft Used: Rs." + (-balance));
                    }
            } 
        else 
            {
                System.out.println("Cannot withdraw. Amount exceeded overdraft limit.");
            }
    }
}