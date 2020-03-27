
package billsplitter.dao;

import billsplitter.domain.Bill;
import java.util.List;

public interface BillDao {
    
    Bill create(Bill bill) throws Exception;
    
    List<Bill> getAll();
    
}
