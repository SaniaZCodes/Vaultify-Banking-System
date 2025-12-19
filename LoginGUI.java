import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class LoginGUI extends JDialog 
{
    private ArrayList<Account> accountList;
    public LoginGUI(ArrayList<Account> accountList) 
    {
        this.accountList = accountList;
        setTitle("Login");
        setSize(400, 200); 
        setLocationRelativeTo(null);
        setModal(true);


        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(135, 206, 235));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Account Number
        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(new JLabel("Account Number:"), gbc);

        JTextField accountNumberField = new JTextField(15);

        // accountNumberField.setPreferredSize(new Dimension(300, 40)); // Removed fixed dimensions for better layout

        gbc.gridx = 1;

        panel.add(accountNumberField, gbc);


        // PIN
        gbc.gridx = 0;
        gbc.gridy = 1;

        panel.add(new JLabel("PIN:"), gbc);

        JPasswordField pinField = new JPasswordField(15);

        // pinField.setPreferredSize(new Dimension(300, 40)); // Removed fixed dimensions for better layout

        gbc.gridx = 1;

        panel.add(pinField, gbc);


        // Login button

        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(50, 205, 50));
        loginButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        panel.add(loginButton, gbc);

        loginButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String accountNumber = accountNumberField.getText().trim();
                String pin = new String(pinField.getPassword());
                if (accountNumber.isEmpty() || pin.isEmpty()) 
                    {
                        JOptionPane.showMessageDialog(null, "Please enter both Account Number and PIN.");
                        return;
                    }

                Account loggedInAccount = null;

                for (Account account : accountList) 
                    {
                        if (accountNumber.equals(account.getAccountNumber()) && pin.equals(account.getPin())) 
                            {
                                loggedInAccount = account;
                                break;
                            }
                    }

                if (loggedInAccount != null) 
                    {
                        JOptionPane.showMessageDialog(null, "Login Successful!");
                        dispose(); 

                        // Open the appropriate dashboard based on account type

                        if (loggedInAccount instanceof SavingAccount) 
                            {
                                new SavingAccountDashboardGUI((SavingAccount) loggedInAccount, accountList);
                            } 
                        else if (loggedInAccount instanceof CurrentAccount) 
                            {
                                new CurrentAccountDashboardGUI((CurrentAccount) loggedInAccount, accountList);
                            }
                    } 
                else 
                    {
                        JOptionPane.showMessageDialog(null, "Invalid account number or PIN. Try again.");
                    }
            }

        });

        add(panel);
        setVisible(true);
    }
} 