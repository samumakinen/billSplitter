
package billsplitter.domain;

import billsplitter.dao.BillDao;
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
    
    /**
     * Creates a new Bill -object with the given information and
     * calls the create(Bill) -method in FileBillDao.
     *
     * @param title String
     * @param description String
     * @param payers int
     * @param amount double
     * @param result double
     *
     * @see billsplitter.dao.FileBillDao#create(billsplitter.domain.Bill)
     * 
     */
    public void createBill(String title, String description, int payers, double amount, double result) {
        Bill bill = new Bill(this.user.getUsername(), title, description, payers, amount, result);
        try {
            this.fileBillDao.create(bill);
        } catch (Exception e) {
        }
    }
    
    /**
     * Returns all current user's bills by getting all bills from the database
     * with the getAll() -method in FileBillDao
     * and filtering out bills that do not belong to the current user.
     *
     * @see billsplitter.dao.FileBillDao#getAll()
     * 
     * @return List with the bills.
     * 
     */
    public List<Bill> getAll() {
        List<Bill> bills;
        
        try {
            bills = this.fileBillDao.getAll().stream().filter(bill -> {
                return bill.getUsername().equals(this.user.getUsername());
            }).collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
        return bills;
    }
    
    /**
     * Returns a specific bill by the id number.
     *
     * @param id int
     * 
     * @see billsplitter.dao.FileBillDao#getBill(int)
     * 
     * @return Bill with the matching id number.
     * 
     */
    public Bill getBill(int id) {
        try {
            return this.fileBillDao.getBill(id);
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Deletes a bill with matching id number from the database.
     *
     * @param id int
     * @see billsplitter.dao.FileBillDao#delete(int)
     * 
     * 
     */
    public boolean deleteBill(int id) {
        try {
            this.fileBillDao.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
