
package billsplitter.domain;

import billsplitter.dao.BillDao;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {
    private final BillDao fileBillDao;
    private User user;
    
    public HistoryService(BillDao billDao) {
        this.fileBillDao = billDao;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public List<Bill> getAll() {
        List<Bill> list = new ArrayList<>();
        try {
            list = this.fileBillDao.getAll();
        } catch (Exception ex) {
            System.out.println("ERROR @ HistoryService.getAll.this.fileBillDao.getAll()");
        }
        return list;
    }
    
    
}
