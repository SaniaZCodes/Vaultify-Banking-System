import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class SavingAccountDashboardGUI extends JFrame 
{
    private SavingAccount account;
    private ArrayList<Account> accountList;

    public SavingAccountDashboardGUI(SavingAccount account, ArrayList<Account> accountList) 
    {
        this.account = account;
        this.accountList = accountList;
        setTitle("Saving Account Dashboard");
        setSize(500, 400);
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
                    account.withdraw(amount);
                    Persistence.saveAccounts(accountList);
                    JOptionPane.showMessageDialog(null, "Withdraw logic executed.");
                } 
                catch (NumberFormatException ex) 
                {
                    JOptionPane.showMessageDialog(null, "Invalid amount.");
                }

            });

        panel.add(withdrawButton);


        // Calculate Interest Button

        JButton interestButton = new JButton("Calculate Interest");
        interestButton.setBackground(new Color(50, 205, 50));
        interestButton.setForeground(Color.WHITE);
        interestButton.addActionListener(e -> 
            {
                String[] options = {"Annual Interest (4.0%)", "Monthly Interest (0.33%)"};
                int choice = JOptionPane.showOptionDialog(null, "Choose interest calculation type:", 
                                                        "Calculate Interest",
                                                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, 
                                                        null, options, options[0]);
                if (choice == 0) 
                    { // Annual
                        double annualInterest = account.getBalance() * (4.0 / 100);
                        account.balance += annualInterest;
                        account.history.add(new Transaction("Annual Interest Applied", annualInterest));
                        Persistence.saveAccounts(accountList);
                        JOptionPane.showMessageDialog(null, "Annual Interest: Rs." + String.format("%.2f", annualInterest) + "\nUpdated Balance: Rs." + String.format("%.2f", account.getBalance()));
                    } 
                else if (choice == 1) 
                    { // Monthly
                        double monthlyInterest = account.getBalance() * ((4.0 / 12) / 100);
                        account.balance += monthlyInterest;
                        account.history.add(new Transaction("Monthly Interest Applied", monthlyInterest));
                        Persistence.saveAccounts(accountList);
                        JOptionPane.showMessageDialog(null, "Monthly Interest: Rs." + String.format("%.2f", monthlyInterest) + "\nUpdated Balance: Rs." + String.format("%.2f", account.getBalance()));
                    }

            });

        panel.add(interestButton);


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
                String details= "Account Number: " + account.getAccountNumber() + "\n" +
                                "Name: " + account.getName() + "\n" +
                                "Account Type: Savings\n" +
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
                                .reduce("", (a, b) -> a + b + "\n");

                JTextArea textArea = new JTextArea(history);
                JScrollPane scrollPane = new JScrollPane(textArea);  
                
                textArea.setLineWrap(true);  
                textArea.setWrapStyleWord(true);
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