
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
    
    public void createBill(String title, String description, int payers, double amount, double result) {
        Bill bill = new Bill(this.user.getUsername(), title, description, payers, amount, result);
        try {
            this.fileBillDao.create(bill);
        } catch (Exception e) {
            System.out.println("HistoryService.createBill() " + e);
        }
    }
    
    public List<Bill> getAll() {
        List<Bill> bills = new ArrayList<>();
        
        try {
            bills = this.fileBillDao.getAll().stream().filter(bill -> bill.getUsername().equals(this.user.getUsername())).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println("HistoryService.getAll() " + e);
        }
        return bills;
    }
    
    public Bill getBill(int id) {
        try {
            return this.fileBillDao.getBill(id);
        } catch (Exception e) {
            System.out.println("HistoryService.getBill() " + e);
        }
        return null;
    }
    
    public void deleteBill(int id) {
        try {
            this.fileBillDao.delete(id);
        } catch (Exception e) {
            System.out.println("HistoryService.deleteBill() " + e);
        }
    }
    
}
