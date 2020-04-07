
package billsplitter.domain;

import billsplitter.dao.BillDao;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryService {
    private final BillDao fileBillDao;
    private User user;
    
    public HistoryService(BillDao billDao) {
        this.fileBillDao = billDao;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public void createBill(Bill bill) {
        try {
            this.fileBillDao.create(bill);
        } catch (Exception ex) {
            System.out.println("ERROR @ HistoryService.getAll.this.fileBillDao.create(Bill) -> " + ex.getMessage());
        }
    }
    
    public List<Bill> getAll() {
        List<Bill> list = new ArrayList<>();
        try {
            list = this.fileBillDao.getAll().stream().filter(bill -> bill.getUsername().equals(this.user.getUsername())).collect(Collectors.toList());
        } catch (Exception ex) {
            System.out.println("ERROR @ HistoryService.getAll.this.fileBillDao.getAll()");
        }
        
        return list;
    }
    
}
