
package billsplitter.domain;

public class Bill {
    private final int id;
    private final int userId;
    private final String creator;
    private final String title;
    private final String description;
    private final int payers;
    private final double amount;
    private final double result;
    
    public Bill(User user, int id, String title, String description, int payers, double amount) {
        this.id = id;
        this.userId = user.getId();
        this.creator = user.getName();               
        this.title = title;
        this.description = description;
        this.payers = payers;
        this.amount = amount;
        this.result = amount / payers;
    }
    
    public int getId() {
        return this.id;
    }
    public int getUserId() {
        return this.userId;
    }
    public String getCreator() {
        return this.creator;
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
