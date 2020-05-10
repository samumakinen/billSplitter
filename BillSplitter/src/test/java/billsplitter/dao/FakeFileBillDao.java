
package billsplitter.dao;

import billsplitter.domain.Bill;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FakeFileBillDao implements BillDao {
    private final Connection db;
    
    public FakeFileBillDao() throws Exception {
        InputStream input = FileBillDao.class.getClassLoader().getResourceAsStream("settings/FakeConfig.properties");
        
        if (input == null) {
            this.db = DriverManager.getConnection("jdbc:sqlite::memory:");
        } else {
            Properties properties = new Properties();
            properties.load(input);
            this.db = DriverManager.getConnection(properties.getProperty("db.url"));
        }
        
        Statement s = this.db.createStatement();
        s.execute("CREATE TABLE IF NOT EXISTS Bills (id INTEGER PRIMARY KEY, username TEXT, title TEXT, description TEXT, payers INTEGER, amount DOUBLE, result DOUBLE)");
        PreparedStatement p = this.db.prepareStatement("INSERT INTO Bills (username,title,description,payers,amount, result) VALUES (?,?,?,?,?,?)");
        p.setString(1, "tester");
        p.setString(2, "titleHere");
        p.setString(3, "descHere");
        p.setInt(4, 4);
        p.setDouble(5, 10.0);
        p.setDouble(6, 2.5);
        p.execute();
    }
    
    @Override
    public void create(Bill bill) throws Exception {
        PreparedStatement p = this.db.prepareStatement("INSERT INTO Bills (username,title,description,payers,amount, result) VALUES (?,?,?,?,?,?)");
        p.setString(1, bill.getUsername());
        p.setString(2, bill.getTitle());
        p.setString(3, bill.getDescription());
        p.setInt(4, bill.getPayers());
        p.setDouble(5, bill.getAmount());
        p.setDouble(6, bill.getResult());
        p.execute();
    }

    @Override
    public List<Bill> getAll() throws Exception {
        List<Bill> bills = new ArrayList<>();
        Statement s = this.db.createStatement();
        ResultSet r = s.executeQuery("SELECT * FROM Bills");
        while (r.next()) {
            bills.add(new Bill(r.getInt("id"), r.getString("username"), r.getString("title"), r.getString("description"), r.getInt("payers"), r.getDouble("amount"), r.getDouble("result")));
        }
        return bills;
    }

    @Override
    public Bill getBill(int id) throws Exception {
        Bill bill = null;
        PreparedStatement p = this.db.prepareStatement("SELECT * FROM Bills WHERE id = ?");
        p.setInt(1, id);
        ResultSet r = p.executeQuery();
        if (r.next()) {
            bill = new Bill(r.getInt("id"), r.getString("username"), r.getString("title"), r.getString("description"), r.getInt("payers"), r.getDouble("amount"), r.getDouble("result"));
        }
        return bill;
    }

    @Override
    public void delete(int id) throws Exception {
        PreparedStatement p = this.db.prepareStatement("DELETE FROM Bills WHERE id = ?");
        p.setInt(1, id);
        p.execute();
    }
    
}
