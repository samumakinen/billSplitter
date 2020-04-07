
package billsplitter.domain;

import billsplitter.dao.UserDao;

public class LoginService {
    private final UserDao fileUserDao;
    
    public LoginService(UserDao userDao) {
        this.fileUserDao = userDao;
    }
    
    public User logIn(String username) {
        try {
            return this.fileUserDao.getAll().stream().filter(user -> username.equals(user.getUsername())).findFirst().get();
        } catch (Exception ex) {
            System.out.println("Error @ LoginService.login(" + username + ").this.fileUserDao.getAll() -> " + ex.getMessage());
        }
        return null;
    }
    
    public String createUser(String name, String username) {
        
        if (name.isEmpty() || username.isEmpty()) return "ERROR;Both fields must be filled!";
        
        if (username.length() < 3|| name.length() < 3) return "ERROR;Both name and username need to be at least 3 characters long!";
        
        CharSequence[] illegalChars = { ";", ":", ",", " "};
        for (CharSequence c : illegalChars) {
            if (username.contains(c) && c.equals(" ")) return "ERROR;emptySpace is not allowed in username!";
            if (username.contains(c)) return "ERROR;" + c + " is not allowed in username!";
        }
        if (name.contains(";")) return "ERROR;; is not allowed in name!";
        
        try {
            if (this.fileUserDao.getAll().stream().anyMatch(u -> u.getUsername().equals(username))) return "ERROR;Username already in use!";
        } catch (Exception ex) {
            System.out.println("Error @ LoginService.createUser(" + name + ", " + username + ").this.fileUserDao.getAll() -> " + ex.getMessage());
        }
        
        try {
            this.fileUserDao.create(new User(name, username));
        } catch (Exception ex) {
            System.out.println("Error @ LoginService.createUser(" + name + ", " + username + ").this.fileUserDao.create() -> " + ex.getMessage());
        }
        return "SUCCESS;User " + name +" has been created with username " + username;
    }

}
