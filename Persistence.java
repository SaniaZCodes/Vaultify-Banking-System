import java.io.*;
import java.util.ArrayList;

public class Persistence 
{
    public static void saveAccounts(ArrayList<Account> accountList) 
    {
        try (FileOutputStream output = new FileOutputStream("accounts.ser");
             ObjectOutputStream objectOutput = new ObjectOutputStream(output)) // turns into bytes to make file easily to write in files
            {
                objectOutput.writeObject(accountList);
            } 
        catch (IOException e) 
        {
            System.out.println("Error saving accounts: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static ArrayList<Account> loadAccounts() 
    {
        try (FileInputStream input = new FileInputStream("accounts.ser");
             ObjectInputStream objectInput = new ObjectInputStream(input)) 
            {
                ArrayList<Account> accountList = (ArrayList<Account>) objectInput.readObject();
                return accountList;
            } 
        catch (IOException | ClassNotFoundException e) 
        {
            return new ArrayList<>();
        }
    }
}