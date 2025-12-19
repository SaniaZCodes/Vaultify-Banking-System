import java.util.ArrayList;
import javax.swing.SwingUtilities;

public class Main 
{
    static ArrayList<Account> accountList = new ArrayList<>();

    public static void main(String[] args) 
    {
        // Load existing accounts from file
        accountList = Persistence.loadAccounts();

        // Launch the GUI application on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> 
        {
            new MainGUI(accountList);
        });
    }
}