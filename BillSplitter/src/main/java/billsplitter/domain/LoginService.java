
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
            System.out.println("Error @ LoginService.login().this.fileUserDao.getAll() -> " + ex.getMessage());
        }
        return null;
    }
    
    public String createUser(String name, String username) {
        
        if (userDetailsTooShort(name, username)) {
            return "ERROR;Both name and username need to be at least 3 characters long!";
        }
        
        if (userDetailsHaveIllegalCharacter(name, username)) {
            return "ERROR;Name or username contains invalid characters!";
        }
        
        if (usernameExists(username)) {
            return "ERROR;Username already in use!";
        }
        
        try {
            this.fileUserDao.create(new User(name, username));
        } catch (Exception ex) {
            System.out.println("Error @ LoginService.createUser().this.fileUserDao.create() -> " + ex.getMessage());
        }
        return "SUCCESS;User " + name + " has been created with username " + username;
    }
    
    private boolean userDetailsTooShort(String name, String username) {
        return name.length() < 3 || username.length() < 3;
    }
    
    private boolean userDetailsHaveIllegalCharacter(String name, String username) {
        CharSequence[] illegalChars = { ";", ":", ","};
        for (CharSequence c : illegalChars) {
            if (username.contains(c) || name.contains(c)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean usernameExists(String username) {
        try {
            if (this.fileUserDao.getAll().stream().anyMatch(u -> u.getUsername().equals(username))) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("Error @ LoginService.createUser().this.fileUserDao.getAll() -> " + ex.getMessage());
        }
        return false;
    }

}
