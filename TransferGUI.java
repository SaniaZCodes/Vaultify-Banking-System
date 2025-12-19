import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class TransferGUI extends JDialog 
{
    private Account sender;
    private ArrayList<Account> accountList;

    public TransferGUI(Account sender, ArrayList<Account> accountList) 
    {
        this.sender = sender;
        this.accountList = accountList;
        setTitle("Transfer Money");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setModal(true);


        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(135, 206, 235));


        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);


        // Receiver Account Number

        gbc.gridx = 0;
        gbc.gridy = 0;

        panel.add(new JLabel("Receiver Acc No:"), gbc);

        JTextField receiverAccField = new JTextField(15);

        gbc.gridx = 1;

        panel.add(receiverAccField, gbc);


        // Amount

        gbc.gridx = 0;
        gbc.gridy = 1;

        panel.add(new JLabel("Amount (Rs.):"), gbc);

        JTextField amountField = new JTextField(15);

        gbc.gridx = 1;

        panel.add(amountField, gbc);


        // Transfer button

        JButton transferButton = new JButton("Transfer");
        transferButton.setBackground(new Color(50, 205, 50));
        transferButton.setForeground(Color.WHITE);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;

        panel.add(transferButton, gbc);

        transferButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String receiverAccountNumber = receiverAccField.getText().trim();
                String amountStr = amountField.getText().trim();
                if (receiverAccountNumber.isEmpty() || amountStr.isEmpty()) 
                    {
                        JOptionPane.showMessageDialog(null, "All fields are required.");
                        return;
                    }

                try 
                {
                    double amount = Double.parseDouble(amountStr);
                    if (amount <= 0) 
                        {
                            JOptionPane.showMessageDialog(null, "Invalid amount.");
                            return;
                        }

                    if (receiverAccountNumber.equals(sender.getAccountNumber())) 
                        {
                            JOptionPane.showMessageDialog(null, "Cannot transfer money to your own account. Use deposit instead.");
                            return;
                        }

                    Account receiver = null;

                    for (Account account : accountList) 
                        {
                            if (account.getAccountNumber().equals(receiverAccountNumber)) 
                                {
                                    receiver = account;
                                    break;
                                }
                        }

                    if (receiver == null) 
                        {
                            JOptionPane.showMessageDialog(null, "Receiver account not found.");
                            return;
                        }

                    if (sender.getBalance() >= amount) 
                        {
                            sender.balance -= amount;
                            receiver.balance += amount;

                            sender.history.add(new Transaction("Transfer to " + receiver.getName() + " (" + receiver.getAccountNumber() + ")", amount));
                            receiver.history.add(new Transaction("Transfer from " + sender.getName() + " (" + sender.getAccountNumber() + ")", amount));
                            Persistence.saveAccounts(accountList);
                            JOptionPane.showMessageDialog(null, "Transfer successful!");
                            dispose();
                        } 
                    else 
                        {
                            JOptionPane.showMessageDialog(null, "Insufficient balance for transfer.");
                        }
                } 
                catch (NumberFormatException ex) 
                {
                    JOptionPane.showMessageDialog(null, "Invalid amount. Please enter a number.");
                }

            }

        });

        add(panel);
        setVisible(true);
    }
} 