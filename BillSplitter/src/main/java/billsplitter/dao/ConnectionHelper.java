
package billsplitter.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionHelper {
    private static Connection c;
    
    public ConnectionHelper() throws Exception {
        InputStream input = ConnectionHelper.class.getClassLoader().getResourceAsStream("settings/config.properties");
        if (input == null) {
            ConnectionHelper.c = DriverManager.getConnection("jdbc:sqlite:billsplitter.db");
        } else {
            Properties properties = new Properties();
            properties.load(input);
            ConnectionHelper.c = DriverManager.getConnection(properties.getProperty("db.url"));
        }
    }
    
    public static Connection getConnection() throws Exception {
        return ConnectionHelper.c;
    }
}
