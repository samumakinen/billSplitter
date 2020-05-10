
package billsplitter.domain;

public class User {
    private final String name;
    private final String username;
    
    public User(String name, String username) {
        this.name = name;
        this.username = username;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public boolean equals(Object obj) {
        
        if (obj == this) {
            return true; 
        }
        
        if (!(obj instanceof User)) {
            return false; 
        }
        
        User user = (User) obj;
        
        return this.name.equals(user.getName()) && this.username.equals(user.getUsername());
    }
}
