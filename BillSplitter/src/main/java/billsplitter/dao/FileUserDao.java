
package billsplitter.dao;

import billsplitter.domain.User;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class FileUserDao implements UserDao {
    private final Connection db;
    
    public FileUserDao() throws Exception {
        InputStream input = FileBillDao.class.getClassLoader().getResourceAsStream("settings/config.properties"); 
        
        if (input == null) {
            this.db = DriverManager.getConnection("jdbc:sqlite:billsplitter.db");
        } else {
            Properties properties = new Properties();
            properties.load(input);
            this.db = DriverManager.getConnection(properties.getProperty("db.url"));
        }
        Statement s = db.createStatement();
        s.execute("CREATE TABLE IF NOT EXISTS Users (username TEXT PRIMARY KEY, name TEXT)");
    }

    @Override
    public void create(User user) throws Exception {
        PreparedStatement p = this.db.prepareStatement("INSERT INTO Users (username,name) VALUES (?,?)");
        p.setString(1, user.getUsername());
        p.setString(2, user.getName());
        p.execute();
    }

    @Override
    public List<User> getAll() throws Exception {
        List<User> users = new ArrayList<>();
        Statement s = db.createStatement();
        
        ResultSet r = s.executeQuery("SELECT * FROM Users");
        while (r.next()) {
            users.add(new User(r.getString("name"), r.getString("username")));
        }
        return users;
    }
    
    @Override
    public User getUser(String username) throws Exception {
        PreparedStatement p = this.db.prepareStatement("SELECT name FROM Users WHERE username = ?");
        p.setString(1, username);
        ResultSet r = p.executeQuery();
        return new User(r.getString("name"), username);
    }
    
}
