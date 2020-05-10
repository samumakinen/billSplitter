
package billsplitter.domain;

import billsplitter.dao.FakeFileBillDao;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class HistoryServiceTest {
    HistoryService historyService;
    
    @Before
    public void setup() throws Exception {
        this.historyService = new HistoryService(new FakeFileBillDao());
        this.historyService.setUser(new User("Test", "tester"));
    }
    
    @Test
    public void getUser() {
        assertEquals(new User("Test", "tester"), this.historyService.getUser());
    }
    
    @Test
    public void createBill() {
        this.historyService.createBill("TestTitle", "TestDesc", 1, 2.0, 1.0);
        assertEquals("tester", this.historyService.getBill(2).getUsername());
        assertEquals("TestTitle", this.historyService.getBill(2).getTitle());
    }
    
    @Test
    public void getAll() {
        assertEquals("titleHere", this.historyService.getAll().get(0).getTitle());
    }
    
    @Test
    public void getBillWhenBillDoesNotExist() {
        assertEquals(null, this.historyService.getBill(55));
    }
    
    @Test
    public void getBillWhenBilltExists() {
        assertEquals("titleHere", this.historyService.getBill(1).getTitle());
    }
    
    @Test
    public void deleteBillWhenBillDoesNotExist() {
        assertTrue(this.historyService.deleteBill(12));
    }
    
    @Test
    public void deleteBillWhenBillExists() {
        assertTrue(this.historyService.deleteBill(1));
    }
    
}
