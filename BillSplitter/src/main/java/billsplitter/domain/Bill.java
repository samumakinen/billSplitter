
package billsplitter.domain;

public class Bill {
    private final String title;
    private final String description;
    private final int payers;
    private final double amount;
    private final double result;
    
    public Bill(User user, String title, String description, int payers, double amount, double result) {              
        this.title = title;
        this.description = description;
        this.payers = payers;
        this.amount = amount;
        this.result = result;
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
