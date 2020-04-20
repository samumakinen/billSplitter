
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
        s.execute("CREATE TABLE IF NOT EXISTS Bills (id INTEGER PRIMARY KEY, username TEXT, title TEXT, description TEXT, payers INTEGER, amount DOUBLE)");
    }

    @Override
    public void create(Bill bill) throws Exception {
        PreparedStatement p = this.db.prepareStatement("INSERT INTO Bills (username,title,description,payers,amount) VALUES (?,?,?,?,?)");
        p.setString(1, bill.getUsername());
        p.setString(2, bill.getTitle());
        p.setString(3, bill.getDescription());
        p.setInt(4, bill.getPayers());
        p.setDouble(5, bill.getAmount());
        p.execute();
    }

    @Override
    public List<Bill> getAll() throws Exception {
        List<Bill> bills = new ArrayList<>();
        Statement s = this.db.createStatement();
        ResultSet r = s.executeQuery("SELECT * FROM Bills");
        while (r.next()) {
            bills.add(new Bill(r.getInt("id"), r.getString("username"), r.getString("title"), r.getString("description"), r.getInt("payers"), r.getDouble("amount")));
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
            bill = new Bill(r.getInt("id"), r.getString("username"), r.getString("title"), r.getString("description"), r.getInt("payers"), r.getDouble("amount"));
        }
        return bill;
    }
}
