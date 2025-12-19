import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class CreateAccountGUI extends JDialog 
{
    private ArrayList<Account> accountList;
    public CreateAccountGUI(ArrayList<Account> accountList) 
    {
        this.accountList = accountList;
        setTitle("Create Account");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setModal(true); // Blocks input to other windows until closed


        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(135, 206, 235));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // space
        // gridx= column index      gridy= row index
        // Account Number
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Account Number:"), gbc);
        JTextField accountNumberField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(accountNumberField, gbc);

        // Name
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Name:"), gbc);
        JTextField nameField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(nameField, gbc);

        // Balance
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Balance:"), gbc);
        JTextField balanceField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(balanceField, gbc);

        // PIN
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("PIN:"), gbc);
        JPasswordField pinField = new JPasswordField(15);
        gbc.gridx = 1;
        panel.add(pinField, gbc);

        // Account Type
        gbc.gridx = 0;
        gbc.gridy = 4;
        panel.add(new JLabel("Account Type:"), gbc);
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Saving", "Current"});
        gbc.gridx = 1;
        panel.add(typeCombo, gbc);

        // Create button
        JButton createButton = new JButton("Create Account");
        createButton.setBackground(new Color(50, 205, 50));
        createButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        panel.add(createButton, gbc);

        createButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String accountNumber = accountNumberField.getText().trim();
                String name = nameField.getText().trim();
                String balanceStr = balanceField.getText().trim();
                String pin = new String(pinField.getPassword());
                String type = (String) typeCombo.getSelectedItem();

                // Validation

                if (accountNumber.isEmpty() || name.isEmpty() || balanceStr.isEmpty() || pin.isEmpty()) 
                    {
                        JOptionPane.showMessageDialog(null, "All fields are required.");
                        return;
                    }

                try 
                {
                    double balance = Double.parseDouble(balanceStr);
                    // Check for duplicate account number
                    for (Account existingAccount : accountList) 
                        {
                            if (existingAccount.getAccountNumber().equals(accountNumber)) 
                                {
                                    JOptionPane.showMessageDialog(null, "Account number already exists.");
                                    return;
                                }
                        }

                    Account newAccount = null;
                    if ("Saving".equals(type)) 
                        {
                            newAccount = new SavingAccount(accountNumber, name, pin, balance);
                        } 
                    else if ("Current".equals(type)) 
                        {
                            newAccount = new CurrentAccount(accountNumber, name, pin, balance);
                        }

                    if (newAccount != null) 
                        {
                            accountList.add(newAccount);
                            Persistence.saveAccounts(accountList);
                            JOptionPane.showMessageDialog(null, "Account created successfully!");
                            dispose();
                        }

                } 
                catch (NumberFormatException ex) 
                {
                    JOptionPane.showMessageDialog(null, "Invalid balance. Please enter a number.");
                }

            }

        });
        add(panel);
        setVisible(true);
    }
} 