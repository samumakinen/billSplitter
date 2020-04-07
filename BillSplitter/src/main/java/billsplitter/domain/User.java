
package billsplitter.domain;

import java.util.Objects;

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
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.username);
        return hash;
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
