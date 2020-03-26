
package billsplitter.domain;

public class User {
    private final int id;
    private final String username;
    private final String name;
    
    public User(int id, String username, String name) {
        this.id = id;
        this.username = username;
        this.name = name;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getName() {
        return this.name;
    }
    
}
