
package billsplitter.dao;

import billsplitter.domain.Bill;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public final class FileBillDao implements BillDao {
    private final Connection db;

    public FileBillDao() throws Exception {
        this.db = ConnectionHelper.getConnection();
        Statement s = this.db.createStatement();
        s.execute("CREATE TABLE IF NOT EXISTS Bills (id INTEGER PRIMARY KEY, username TEXT, title TEXT, description TEXT, payers INTEGER, amount DOUBLE, result DOUBLE)");
    }

    /**
     * Tries to create a new Bill into the database.
     * 
     * @param bill Bill to be created
     * @throws java.lang.Exception SQLException
     * 
     */
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

    /**
     * Tries to get all bills from the database.
     * 
     * @throws java.lang.Exception SQLException
     * 
     * @return List of all bills.
     * 
     */
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

    /**
     * Tries to get a specific bill from the database by bill's id number.
     * 
     * @param id int
     * 
     * @throws java.lang.Exception SQLException
     * 
     * @return Bill with a matching id number.
     * 
     */
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

    /**
     * Tries to delete a specific bill from the database by id number.
     * 
     * @param id int
     * 
     * @throws java.lang.Exception SQLException
     * 
     */
    @Override
    public void delete(int id) throws Exception {
        PreparedStatement p = this.db.prepareStatement("DELETE FROM Bills WHERE id = ?");
        p.setInt(1, id);
        p.execute();
    }
}
