
package billsplitter.domain;

import billsplitter.dao.UserDao;

public class LoginService {
    private final UserDao fileUserDao;
    
    public LoginService(UserDao userDao) {
        this.fileUserDao = userDao;
    }
    
    public User logIn(String username) {
        try {
            return this.fileUserDao.getUser(username);
        } catch (Exception e) {
            System.out.println("LoginService.logIn() " + e);
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
        } catch (Exception e) {
            System.out.println("LoginService.createUser() " + e);
        }
        return "SUCCESS;User " + name + " has been created with username " + username;
    }
    
    private boolean userDetailsTooShort(String name, String username) {
        return name.length() < 3 || username.length() < 3;
    }
    
    private boolean userDetailsHaveIllegalCharacter(String name, String username) {
        CharSequence[] illegalChars = { ";", ":", ",", "'"};
        for (CharSequence c : illegalChars) {
            if (username.contains(c) || name.contains(c)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean usernameExists(String username) {
        try {
            return this.fileUserDao.getUser(username) != null;
        } catch (Exception e) {
            System.out.println("LoginService.usernameExists() " + e);
        }
        return false;
    }

}
