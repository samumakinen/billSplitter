
package billsplitter.dao;

import billsplitter.domain.Bill;
import java.util.List;

public interface BillDao {
    
    void create(Bill bill) throws Exception;

    List<Bill> getAll() throws Exception;
    
    public Bill getBill(int id) throws Exception;
    
}
