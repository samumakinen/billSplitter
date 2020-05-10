
package billsplitter.domain;

public class Bill {
    private int id;
    private final String username;
    private final String title;
    private final String description;
    private final int payers;
    private final double amount;
    private final double result;
    
    public Bill(String username, String title, String description, int payers, double amount, double result) {   
        this.username = username;
        this.title = title;
        this.description = description;
        this.payers = payers;
        this.amount = amount;
        this.result = result;
    }
    
    public Bill(int id, String username, String title, String description, int payers, double amount, double result) {   
        this.id = id;
        this.username = username;
        this.title = title;
        this.description = description;
        this.payers = payers;
        this.amount = amount;
        this.result = result;
    }
    
    @Override
    public String toString() {
        return this.getTitle();
    }
    
    public int getId() {
        return this.id;
    }
    public String getUsername() {
        return this.username;
    }
    public String getTitle() {
        return this.title;
    }
    public String getDescription() {
        return this.description;
    }
    public int getPayers() {
        return this.payers;
    }
    public double getAmount() {
        return this.amount;
    }
    public double getResult() {
        return this.result;
    }
}
