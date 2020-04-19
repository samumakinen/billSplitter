
package billsplitter.domain;

import billsplitter.dao.BillDao;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            Logger.getLogger(HistoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Bill> getAll() {
        List<Bill> bills = new ArrayList<>();
        
        try {
            bills = this.fileBillDao.getAll().stream().filter(bill -> bill.getUsername().equals(this.user.getUsername())).collect(Collectors.toList());
        } catch (Exception ex) {
            Logger.getLogger(HistoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bills;
    }
    
}
