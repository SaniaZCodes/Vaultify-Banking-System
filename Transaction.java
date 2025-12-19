import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction implements Serializable 
{
    private static final long serialVersionUID = 1L;
    private String type;
    private double amount;
    private String date;

    public Transaction(String type, double amount) 
    {
        this.type = type;
        this.amount = amount;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getDate() 
    {
        return date;
    }

    public String getType() 
    {
        return type;
    }

    public double getAmount() 
    {
        return amount;
    }
}