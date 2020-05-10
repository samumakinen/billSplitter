
package billsplitter.dao;

import billsplitter.domain.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class FileUserDao implements UserDao {
    private final Connection db;

    public FileUserDao() throws Exception {
        this.db = ConnectionHelper.getConnection();
        Statement s = this.db.createStatement();
        s.execute("CREATE TABLE IF NOT EXISTS Users (username TEXT PRIMARY KEY, name TEXT)");
    }

    /**
     * Tries to create a new User into the database.
     * 
     * @param user User to be created
     * @throws java.lang.Exception SQLException
     * 
     */
    @Override
    public void create(User user) throws Exception {
        PreparedStatement p = this.db.prepareStatement("INSERT INTO Users (username,name) VALUES (?,?)");
        p.setString(1, user.getUsername());
        p.setString(2, user.getName());
        p.execute();
    }

    /**
     * Tries to get all users from the database.
     * 
     * @throws java.lang.Exception SQLException
     * 
     * @return List of all users.
     * 
     */
    @Override
    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        Statement s = this.db.createStatement();
        ResultSet r = s.executeQuery("SELECT * FROM Users");
        while (r.next()) {
            users.add(new User(r.getString("name"), r.getString("username")));
        }
        return users;
    }

    /**
     * Tries to get a specific user from the database by username.
     * 
     * @param username String
     * 
     * @throws java.lang.Exception SQLException
     * 
     * @return User with a matching username.
     * 
     */
    @Override
    public User getUser(String username) throws Exception {
        User user = null;
        PreparedStatement p = this.db.prepareStatement("SELECT name FROM Users WHERE username = ?");
        p.setString(1, username);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            user = new User(r.getString("name"), username);
        }
        return user;
    }
}
