import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class MainGUI extends JFrame 
{
    private ArrayList<Account> accountList;
    public MainGUI(ArrayList<Account> accountList) 
    {
        this.accountList = accountList;
        setTitle("Bank Account Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(25, 25, 112)); 


        // Create panel for buttons

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(25, 25, 112));


        // Title label

        JLabel titleLabel = new JLabel("Welcome to Vaultify");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.YELLOW);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));


        // Create Account button

        JButton createButton = new JButton("Create Account");
        createButton.setFont(new Font("Arial", Font.BOLD, 18));
        createButton.setBackground(new Color(255, 69, 0)); // Red orange
        createButton.setForeground(Color.WHITE);
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createButton.setMaximumSize(new Dimension(250, 60));
        createButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                new CreateAccountGUI(accountList);
            }
        });

        panel.add(createButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Login button

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.setBackground(new Color(0, 191, 255)); // Deep sky blue
        loginButton.setForeground(Color.WHITE);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setMaximumSize(new Dimension(250, 60));
        loginButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                new LoginGUI(accountList);
            }

        });

        panel.add(loginButton);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));


        // Exit button

        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.BOLD, 18));
        exitButton.setBackground(new Color(138, 43, 226)); // Blue violet
        exitButton.setForeground(Color.WHITE);
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.setMaximumSize(new Dimension(250, 60));
        exitButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                System.exit(0);
            }

        });

        panel.add(exitButton);

        add(panel);
        setVisible(true);
    }
}