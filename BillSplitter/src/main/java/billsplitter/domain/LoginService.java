
package billsplitter.domain;

import billsplitter.dao.UserDao;

public class LoginService {
    private final UserDao fileUserDao;
    
    public LoginService(UserDao userDao) {
        this.fileUserDao = userDao;
    }
    
    /**
     * Calls FileUserDao's getUser(String) -method and returns a User -object
     * if the username exists.
     * 
     * @param username String
     *
     * @see billsplitter.dao.FileUserDao#getUser(java.lang.String)
     * 
     * @return User object or null
     * 
     */
    public User logIn(String username) {
        try {
            return this.fileUserDao.getUser(username);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Calls FileUserDao's create(User) -method and returns a message stating
     * whether the operation was successful or not.
     * 
     * @param name String
     * @param username String
     *
     * @see billsplitter.dao.FileUserDao#create(billsplitter.domain.User)
     * 
     * @return Message as a String
     * 
     */
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
            return "ERROR;Something went wrong, please try again!";
        }
        return "SUCCESS;User " + name + " has been created with username " + username;
    }

    /**
     * Checks if the given name and username are too short.
     * 
     * @param name String
     * @param username String
     * 
     * @return boolean
     * 
     */
    private boolean userDetailsTooShort(String name, String username) {
        return name.length() < 3 || username.length() < 3;
    }
    
    /**
     * Checks if the gicen name and username have illegal characters.
     * 
     * @param name String
     * @param username String
     * 
     * @return boolean
     * 
     */
    private boolean userDetailsHaveIllegalCharacter(String name, String username) {
        CharSequence[] illegalChars = { ";", ":", ",", "'"};
        for (CharSequence c : illegalChars) {
            if (username.contains(c) || name.contains(c)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Calls FileUserDao's getUser(String) -method and returns
     * true if username already exists.
     * 
     * @param username String
     *
     * @see billsplitter.dao.FileUserDao#getUser(java.lang.String)
     * 
     * @return boolean
     * 
     */
    private boolean usernameExists(String username) {
        try {
            return this.fileUserDao.getUser(username) != null;
        } catch (Exception e) {
            return false;
        }
    }
}
