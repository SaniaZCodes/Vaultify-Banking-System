import javax.swing.*;
import java.awt.*;
import java.util.*;
public class CurrentAccountDashboardGUI extends JFrame 
{
    private CurrentAccount account;
    private ArrayList<Account> accountList;

    public CurrentAccountDashboardGUI(CurrentAccount account, ArrayList<Account> accountList) 
    {
        this.account = account;
        this.accountList = accountList;
        setTitle("Current Account Dashboard");
        setSize(500, 450); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 1, 10, 10));
        panel.setBackground(new Color(135, 206, 235));

        // Deposit Button

        JButton depositButton = new JButton("Deposit");
        depositButton.setBackground(new Color(255, 215, 0));
        depositButton.setForeground(Color.BLACK);
        depositButton.addActionListener(e -> 
            {
                String amountStr = JOptionPane.showInputDialog("Enter deposit amount:");
                try 
                {
                    double amount = Double.parseDouble(amountStr);
                    account.deposit(amount);
                    Persistence.saveAccounts(accountList);
                    JOptionPane.showMessageDialog(null, "Deposit successful!");
                } 
                catch (NumberFormatException ex) 
                {
                    JOptionPane.showMessageDialog(null, "Invalid amount.");
                }

            });

        panel.add(depositButton);

        // Withdraw Button

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setBackground(new Color(255, 215, 0));
        withdrawButton.setForeground(Color.BLACK);
        withdrawButton.addActionListener(e -> 
            {
                String amountStr = JOptionPane.showInputDialog("Enter withdraw amount:");
                try 
                {
                    double amount = Double.parseDouble(amountStr);

                    double projectedBalance = account.getBalance() - amount;
                    if (amount <= 0) 
                        {
                            JOptionPane.showMessageDialog(null, "Invalid withdraw amount.");
                        } 
                    else if (amount > account.getBalance()) 
                        {
                            JOptionPane.showMessageDialog(null, "Insufficient balance.");
                        } 
                    else if (projectedBalance >= 5000) 
                        {
                            account.balance -= amount;
                            account.history.add(new Transaction("Withdraw", amount));
                            Persistence.saveAccounts(accountList);
                            JOptionPane.showMessageDialog(null, "Rs. " + String.format("%.2f", amount) + " withdrawn successfully.");
                        } 
                    else 
                        {
                            JOptionPane.showMessageDialog(null, "Cannot withdraw amount. Projected balance (Rs." + String.format("%.2f", projectedBalance) + ") will be below minimum balance (Rs.5000).");
                        }

                } 
                catch (NumberFormatException ex) 
                {
                    JOptionPane.showMessageDialog(null, "Invalid amount.");
                }

            });

        panel.add(withdrawButton);

        // Overdraft Button

        JButton overdraftButton = new JButton("Overdraft Withdraw");
        overdraftButton.setBackground(new Color(255, 215, 0));
        overdraftButton.setForeground(Color.BLACK);
        overdraftButton.addActionListener(e -> 
            {
                String amountStr = JOptionPane.showInputDialog("Enter overdraft withdraw amount:");
                try 
                {
                    double amount = Double.parseDouble(amountStr);
                    int overdraftLimit = 9000;
                    if (amount <= 0) 
                        {
                            JOptionPane.showMessageDialog(null, "Invalid amount.");
                        } 
                    else if (account.getBalance() - amount >= -overdraftLimit) 
                        {
                            account.balance -= amount;
                            account.history.add(new Transaction("Overdraft Withdraw", amount));
                            Persistence.saveAccounts(accountList);
                            String message = "Overdraft withdraw successful! Current Balance: Rs." + String.format("%.2f", account.getBalance());
                            if (account.getBalance() < 0) 
                                {
                                    message += "\nOverdraft Used: Rs." + String.format("%.2f", -account.getBalance());
                                }

                            JOptionPane.showMessageDialog(null, message);
                        } 
                    else 
                        {
                            JOptionPane.showMessageDialog(null, "Cannot withdraw. Amount exceeded overdraft limit of Rs." + overdraftLimit + ".");
                        }

                } 
                catch (NumberFormatException ex) 
                {
                    JOptionPane.showMessageDialog(null, "Invalid amount.");
                }
            });

        panel.add(overdraftButton);


        // Check Balance Button
        JButton balanceButton = new JButton("Check Balance");
        balanceButton.setBackground(new Color(50, 205, 50));
        balanceButton.setForeground(Color.WHITE);
        balanceButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Balance: Rs." + String.format("%.2f", account.getBalance())));
        panel.add(balanceButton);


        // Account Details Button
        JButton detailsButton = new JButton("Account Details");
        detailsButton.setBackground(new Color(50, 205, 50));
        detailsButton.setForeground(Color.WHITE);
        detailsButton.addActionListener(e -> 
            {
                String details = "Account Number: " + account.getAccountNumber() + "\n" +
                                "Name: " + account.getName() + "\n" +
                                "Account Type: Current\n" +
                                "Balance: Rs." + String.format("%.2f", account.getBalance());

                JOptionPane.showMessageDialog(null, details);

            });

        panel.add(detailsButton);

        // Transaction History Button
        JButton historyButton = new JButton("Transaction History");
        historyButton.setBackground(new Color(50, 205, 50));
        historyButton.setForeground(Color.WHITE);
        historyButton.addActionListener(e -> 
            {
                String history = account.getHistory().isEmpty() ? "No transactions yet." :
                                account.getHistory().stream()
                                .map(t -> t.getDate() + " | " + t.getType() + " | Rs." + String.format("%.2f", t.getAmount()))
                                .reduce("", (a, b) -> a + b + "\n"); // combine in one place

                JTextArea textArea = new JTextArea(history);
                JScrollPane scrollPane = new JScrollPane(textArea);  
                textArea.setLineWrap(true);  // break line
                textArea.setWrapStyleWord(true); // where to break
                scrollPane.setPreferredSize( new Dimension( 350, 200 ) );
                JOptionPane.showMessageDialog(null, scrollPane, "Transaction History", JOptionPane.INFORMATION_MESSAGE);
            });

        panel.add(historyButton);


        // Transfer Money Button

        JButton transferButton = new JButton("Transfer Money");
        transferButton.setBackground(new Color(255, 215, 0));
        transferButton.setForeground(Color.BLACK);
        transferButton.addActionListener(e -> 
            {
                new TransferGUI(account, accountList);
            });

        panel.add(transferButton);


        // Logout Button

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(220, 20, 60));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.addActionListener(e -> dispose());
        panel.add(logoutButton);

        
        add(panel);
        setVisible(true);
    }
}