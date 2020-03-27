
package billsplitter.domain;

import billsplitter.dao.FileUserDao;
import java.util.List;

public class LoginService {
    private final FileUserDao fileUserDao;
    private List<User> users;
    
    public LoginService(FileUserDao fileUserDao) {
        this.fileUserDao = fileUserDao;
        updateUsers();
    }
    
    public boolean userExists(String username) {
        return this.users.stream().anyMatch(user -> username.equals(user.getUsername()));
    }
    
    public String createUser(String name, String username) {
        if (name.isEmpty() || username.isEmpty()) {
            return "ERROR;Both fields must be filled!";
        }
        if (username.length() < 3|| name.length() < 3) {
            return "ERROR;Both name and username need to be at least 3 characters long!";
        }
        if (userExists(username)) {
            return "ERROR;Username already in use!";
        }
        
        this.fileUserDao.create(new User(name, username));
        return "SUCCESS;User " + name +" has been created with username " + username;
    }

    private void updateUsers() {
        this.users = this.fileUserDao.getAll();
    }
    
    
}
