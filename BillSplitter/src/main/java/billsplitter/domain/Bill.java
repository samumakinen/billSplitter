
package billsplitter.domain;

public class Bill {
    private int id;
    private User user;
    private String title;
    private String description;
    private int payers;
    private double sum;
    
    public Bill(int id, User user, String title, String description, int payers, double sum) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.description = description;
        this.payers = payers;
        this.sum = sum;
    }
    
}
